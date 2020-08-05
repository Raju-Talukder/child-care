package com.child.controller;

import com.child.dto.PhotoDto;
import com.child.model.Account;
import com.child.model.Address;
import com.child.service.account.AccountService;
import com.child.service.address.AddressService;
import com.child.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/")
    public String getIndex(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        model.addAttribute("photo",this.photoService.findByPath("Profile"));
        return "admin/index";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        Address address = addressService.findById(account.getId())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        model.addAttribute("address",address);
        model.addAttribute("photo",this.photoService.findByPath("Profile"));
        return "admin/profile";
    }

    @GetMapping("/profile/edit")
    public String getEditProfile(Model model,PhotoDto photoDto){
        model.addAttribute("photoDto",photoDto);
        return "admin/editProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("image") MultipartFile image, PhotoDto photoDto) throws IOException {
        accountService.savePhoto(image,photoDto);
        return "redirect:/admin/profile/edit";
    }

    @GetMapping("/attendance")
    public String getAttendance(Model model){
        return "admin/attendance";
    }

    @GetMapping("/assign-food")
    public String getAssignFood(Model model){
        return "admin/assignFood";
    }
}
