package com.child.controller;

import com.child.dto.AccountCreateDto;
import com.child.model.Account;
import com.child.model.Role;
import com.child.service.account.AccountService;
import com.child.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminEmpController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/add/employee")
    public String getAddEmp(Model model, AccountCreateDto accountCreateDto){
        model.addAttribute("accountCreateDto",accountCreateDto);
        return "admin/addEmp";
    }

    @PostMapping("/add/employee")
    public String addEmp(Model model, AccountCreateDto accountCreateDto){
        accountService.createUserByAdmin(accountCreateDto);
        return "redirect:admin/add/employee";
    }

    @GetMapping("/list/employee")
    public String getEmployeeList( Model model, AccountCreateDto accountCreateDto){
        return "admin/empList";
    }
}
