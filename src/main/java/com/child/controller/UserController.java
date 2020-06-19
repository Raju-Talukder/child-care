package com.child.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String dashboard(){
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
    public String getProfile(){
        return "user/profile";
    }
}
