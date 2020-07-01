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

    @GetMapping("/list/employee")
    public String getEmployeeList(){
        return "admin/empList";
    }

    @GetMapping("/add/employee")
    public String getAddEmp(){
        return "admin/addEmp";
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
