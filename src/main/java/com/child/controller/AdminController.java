package com.child.controller;

import com.child.dto.AccountUpdateDto;
import com.child.service.account.AccountService;
import com.child.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String getIndex(){
        return "admin/index";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        UserController.userDuplicateCode(model, accountService, addressService);
        return "admin/profile";
    }

    @GetMapping("/profile/edit")
    public String getEditProfile(Model model, AccountUpdateDto accountUpdateDto){
        UserController.duplicateCode(model, accountUpdateDto, accountService, addressService);
        return "admin/editProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("image") MultipartFile image, AccountUpdateDto accountUpdateDto) throws IOException {
        accountService.update(image,accountUpdateDto);
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
