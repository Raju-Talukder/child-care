package com.child.dao.contactInfo;

import com.child.model.ContactInfo;
import com.child.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactInfoDaoImp implements ContactInfoDao{
    @Autowired
    private ContactInfoRepository contactInfoRepository;
    @Override
    public Optional<ContactInfo> findById(long id) {
        return contactInfoRepository.findById(id);
    }

    @Override
    public List<ContactInfo> findAll() {
        return contactInfoRepository.findAll();
    }

    @Override
    public ContactInfo create(ContactInfo entity) {
        return contactInfoRepository.save(entity);
    }

    @Override
    public ContactInfo update(ContactInfo entity) {
        return contactInfoRepository.save(entity);
    }

    @Override
    public void delete(ContactInfo entity) {
        contactInfoRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        contactInfoRepository.deleteById(entityId);
    }
}
