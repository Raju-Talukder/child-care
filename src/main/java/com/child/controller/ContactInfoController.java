package com.child.controller;

import com.child.dto.ContactInfoDto;
import com.child.model.ContactInfo;
import com.child.service.contactInfo.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class ContactInfoController {
    @Autowired
    private ContactInfoService contactInfoService;

    @GetMapping(value = "/add/contact")
    public String getAddContact(Model model,ContactInfoDto contactInfoDto){
        model.addAttribute("contactInfoDto",contactInfoDto);
        return "admin/addContactInfo";
    }

    @PostMapping(value = "/add/contact")
    public String AddContact(@Valid ContactInfoDto contactInfoDto){
        contactInfoService.create(contactInfoDto);
        return "redirect:/admin/add/contact";
    }
}
