package com.child.dao.message;

import com.child.model.Message;
import com.child.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageDaoImp implements MessageDao{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message create(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Message update(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public void delete(Message entity) {
        messageRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        messageRepository.deleteById(entityId);
    }
}
