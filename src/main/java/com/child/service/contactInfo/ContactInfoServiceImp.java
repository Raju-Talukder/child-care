package com.child.service.contactInfo;

import com.child.dto.ContactInfoDto;
import com.child.model.ContactInfo;
import com.child.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoServiceImp implements ContactInfoService{
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
    public ContactInfo create(ContactInfoDto entity) {
        String email = entity.getEmail();
        String phone = entity.getPhone();
        String address = entity.getAddress();

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);
        contactInfo.setPhone(phone);
        contactInfo.setAddress(address);
        return contactInfoRepository.save(contactInfo);
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
