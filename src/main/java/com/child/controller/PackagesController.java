package com.child.controller;

import com.child.dto.PackagesDto;
import com.child.service.packages.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PackagesController {
    @Autowired
    private PackagesService packagesService;

    @GetMapping(value = "/add/packages")
    public String getAddPackages(Model model, PackagesDto packagesDto){
        model.addAttribute("packagesDto",packagesDto);
        return "admin/addPackages";
    }

    @PostMapping(value = "/add/packages")
    public String addPackages(Model model, PackagesDto packagesDto){
        packagesService.createPackages(packagesDto);
        return "redirect:/admin/add/packages";
    }
}
