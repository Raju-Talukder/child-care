package com.child.service.message;

import com.child.dto.MessageDto;
import com.child.model.Message;

import java.util.Optional;

public interface MessageService {
    public Message createMessage(MessageDto messageDto);
    Optional<Message> findById(Long id);
}
