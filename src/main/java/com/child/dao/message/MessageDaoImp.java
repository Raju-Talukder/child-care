package com.child.dao.message;

import com.child.model.Message;

import java.util.List;
import java.util.Optional;

public class MessageDaoImp implements MessageDao{
    @Override
    public Optional<Message> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Message create(Message entity) {
        return null;
    }

    @Override
    public Message update(Message entity) {
        return null;
    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
