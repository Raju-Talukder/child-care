package com.child.controller;

import com.child.dto.PhotoDto;
import com.child.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminPhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/add/photos")
    public String getAddPhotos(Model model, PhotoDto photoDto){
        model.addAttribute("photoDto",photoDto);
        return "admin/addPhotos";
    }

    @PostMapping("/add/photos")
    public String addPhotos(@RequestParam("image") MultipartFile image,PhotoDto photoDto) throws IOException {
        photoService.savePhoto(image,photoDto);
        return "redirect:/admin/add/photos";
    }
}
