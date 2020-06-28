package com.child.controller;

import com.child.dao.child.ChildDao;
import com.child.dto.ChildDto;
import com.child.model.Child;
import com.child.service.child.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/child")
public class ChildController {
    @Autowired
    private ChildService childService;

    @GetMapping("/list")
    public String getChildList(Model model,ChildDto childDto){
        model.addAttribute("childDto",this.childService.findAll());
        return "admin/childList";
    }

    @GetMapping("/request")
    public String getChildAccountRequest(){
        return "admin/childAccountRequest";
    }

    @GetMapping("/add")
    public String getAddChild(ChildDto childDto,Model model){
        model.addAttribute("childDto",childDto);
        return "admin/addChild";
    }

    @PostMapping("/add")
    public String addChild(ChildDto childDto,Model model){
        model.addAttribute("childDto",childDto);
        Child child = childService.createChild(childDto);
        childDto.setId(child.getId());
        return "redirect:admin/addChild";
    }

    @GetMapping("/delete/{id}")
    public String deleteChild(@PathVariable Long id){
        childService.deleteById(id);
        return "redirect:/admin/child/list";
    }
}
