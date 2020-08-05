package com.child.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/")
    public String dashboard(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        return "user/index";
    }

    @GetMapping("/child-add")
    public String addChild(){
        return "user/addChild";
    }

    @GetMapping("/child-profile")
    public String childProfile(){
        return "user/childProfile";
    }

    @GetMapping("/daily-report")
    public String dailyReport(){
        return "user/dailyReport";
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
        return "user/profile";
    }
}
