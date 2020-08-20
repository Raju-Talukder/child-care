package com.child.controller;

import com.child.dto.AccountUpdateDto;
import com.child.model.Account;
import com.child.model.Address;
import com.child.service.account.AccountService;
import com.child.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public String dashboard(){
        return "user/index";
    }

    @GetMapping("/daily-report")
    public String dailyReport(){
        return "user/dailyReport";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        userDuplicateCode(model, accountService, addressService);
        return "user/profile";
    }

    static void userDuplicateCode(Model model, AccountService accountService, AddressService addressService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        Address address = addressService.findById(account.getId())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        model.addAttribute("address",address);
    }

    static void duplicateCode(Model model, AccountUpdateDto accountUpdateDto, AccountService accountService, AddressService addressService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName()).get();
        Address address = addressService.findById(account.getId()).get();
        accountUpdateDto.setId(account.getId());
        accountUpdateDto.setFirstName(account.getFirstName());
        accountUpdateDto.setLastName(account.getLastName());
        accountUpdateDto.setPhoto(account.getPhoto());
        accountUpdateDto.setCity(address.getCity());
        accountUpdateDto.setAddress(address.getAddress());
        accountUpdateDto.setZip(address.getZip());
        model.addAttribute("accountUpdateDto",accountUpdateDto);
    }

    @GetMapping("/profile/edit")
    public String getEditProfile(Model model, AccountUpdateDto accountUpdateDto){
        duplicateCode(model, accountUpdateDto, accountService, addressService);
        return "user/editProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("image") MultipartFile image, AccountUpdateDto accountUpdateDto) throws IOException {
        accountService.update(image,accountUpdateDto);
        return "redirect:/user/profile/edit";
    }
}
