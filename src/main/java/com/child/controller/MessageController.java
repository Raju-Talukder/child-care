package com.child.controller;

import com.child.dto.MessageDto;
import com.child.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/contact")
    public String addMessage(MessageDto messageDto){
        messageService.createMessage(messageDto);
        return "redirect:/contact";
    }
}
