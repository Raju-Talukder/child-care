package com.child.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String getIndex(){
        return "admin/index";
    }

    @GetMapping("/profile")
    public String getProfile(){
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
