package com.child.controller;

import com.child.model.Account;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getIndex(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        return "admin/index";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(()->new IllegalArgumentException("Not found"));
        model.addAttribute("profile",account);
        return "admin/profile";
    }

    @GetMapping("/attendance")
    public String getAttendance(){
        return "admin/attendance";
    }

    @GetMapping("/assign-food")
    public String getAssignFood(){
        return "admin/assignFood";
    }
}
