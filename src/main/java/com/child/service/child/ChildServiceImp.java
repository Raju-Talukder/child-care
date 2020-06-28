package com.child.service.child;

import com.child.dao.child.ChildDao;
import com.child.dto.ChildDto;
import com.child.model.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildServiceImp implements ChildService{
    @Autowired
    private ChildDao childDao;

    @Override
    public Child createChild(ChildDto childDto) {
        String name = childDto.getName();
        String gender = childDto.getGender();
        String dateOfBirth = childDto.getDateOfBirth();

        Child child = new Child();
        child.setName(name);
        child.setDateOfBirth(dateOfBirth);
        child.setGender(gender);

        return childDao.create(child);
    }

    @Override
    public Optional<Child> findById(Long id) {
        return childDao.findById(id);
    }

    @Override
    public List<Child> findAll() {
        return childDao.findAll();
    }

    @Override
    public void deleteById(long entityId) {
        childDao.deleteById(entityId);
    }
}
