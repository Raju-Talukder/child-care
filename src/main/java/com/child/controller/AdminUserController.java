package com.child.controller;

import com.child.dto.AccountCreateDto;
import com.child.model.Account;
import com.child.model.Address;
import com.child.service.account.AccountService;
import com.child.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/add/user")
    public String getAddUser(Model model,AccountCreateDto accountCreateDto){
        model.addAttribute("accountCreateDto",accountCreateDto);
        return "admin/addUser";
    }

    @PostMapping("/add/user")
    public String addUser(Model model,AccountCreateDto accountCreateDto){
        accountService.createUserByAdmin(accountCreateDto);
        return "redirect:/admin/add/user";
    }

    @GetMapping("/list/user")
    public String getUserList(Model model, AccountCreateDto accountCreateDto){
        model.addAttribute("accountCreateDto",this.accountService.findAll());
        return "admin/userList";
    }

    @GetMapping("/{id}/view")
    public String getInfo(@PathVariable("id") Long id, Model model){
        Account account = accountService.findById(id).get();
        Address address = addressService.findById(id).get();
        model.addAttribute("account", account);
        model.addAttribute("address", address);
        return "admin/info";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        accountService.delete(id);
        return "redirect:/admin/list/user";
    }

    @GetMapping("/request/user")
    public String getUserAccountRequest(){
        return "admin/userAccountRequest";
    }


}
