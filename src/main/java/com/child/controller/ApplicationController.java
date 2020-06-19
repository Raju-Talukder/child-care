package com.child.controller;

import com.child.dto.AccountCreateDto;
import com.child.dto.CodeVerifyDto;
import com.child.model.Account;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(AccountCreateDto accountCreateDto, Model model) {
        return "register";
    }

    @PostMapping("sign-up")
    public String signUp(@Valid AccountCreateDto accountCreateDto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "sign-up";
        }
        Account account = accountService.createMember(accountCreateDto);
        accountCreateDto.setId(account.getId());
        return "redirect:/verify-code";
    }

    @GetMapping("/verify-code")
    public String verifyCode(Model model, CodeVerifyDto codeVerifyDto) {
        return "verify-code";
    }

    @PostMapping("verify-code")
    public String verifyCodeAction(Model model,@Valid  CodeVerifyDto codeVerifyDto, BindingResult result) {
        if(result.hasErrors()) {
            return "verify-code";
        }
        accountService.verifyCode(codeVerifyDto);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(Model model){
        return "home/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "home/contact";
    }

    @GetMapping("/about")
    public String about(){
        return "home/about";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "home/gallery";
    }
}
