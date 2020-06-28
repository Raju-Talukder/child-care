package com.child.service.contactInfo;

import com.child.dao.contactInfo.ContactInfoDao;
import com.child.dto.ContactInfoDto;
import com.child.model.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoServiceImp implements ContactInfoService{
    @Autowired
    private ContactInfoDao contactInfoDao;

    @Override
    public Optional<ContactInfo> findById(long id) {
        return contactInfoDao.findById(id);
    }

    @Override
    public List<ContactInfo> findAll() {
        return contactInfoDao.findAll();
    }

    @Override
    public ContactInfo create(ContactInfoDto entity) {
        String email = entity.getEmail();
        String phone = entity.getPhone();
        String address = entity.getAddress();

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);
        contactInfo.setPhone(phone);
        contactInfo.setAddress(address);
        return contactInfoDao.create(contactInfo);
    }

    @Override
    public ContactInfo update(ContactInfo entity) {
        return contactInfoDao.update(entity);
    }

    @Override
    public void delete(ContactInfo entity) {
        contactInfoDao.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        contactInfoDao.deleteById(entityId);
    }
}
