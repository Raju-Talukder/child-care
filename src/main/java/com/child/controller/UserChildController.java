package com.child.controller;

import com.child.dto.ChildDto;
import com.child.service.child.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserChildController {
    @Autowired
    private ChildService childService;

    @GetMapping("/child-add")
    public String addChild(ChildDto childDto, Model model){
        model.addAttribute("childDto",childDto);
        return "user/addChild";
    }

    @PostMapping("/child-add")
    public String addChild(ChildDto childDto, MultipartFile image) throws Exception {
        childService.createChild(image,childDto);
        return "redirect:/user/child-add";
    }

    @GetMapping("/child-profile")
    public String childProfile(Model model){
        model.addAttribute("childProfile",this.childService.findAlByParents());
        return "user/childProfile";
    }
}
