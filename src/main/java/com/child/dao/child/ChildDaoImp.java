package com.child.dao.child;

import com.child.model.Child;
import com.child.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChildDaoImp implements ChildDao{
    @Autowired
    private ChildRepository childRepository;

    @Override
    public Optional<Child> findById(long id) {
        return childRepository.findById(id);
    }

    @Override
    public List<Child> findAll() {
        return childRepository.findAll();
    }

    @Override
    public Child create(Child entity) {
        return childRepository.save(entity);
    }

    @Override
    public Child update(Child entity) {
        return childRepository.save(entity);
    }

    @Override
    public void delete(Child entity) {
        childRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        childRepository.deleteById(entityId);
    }
}
