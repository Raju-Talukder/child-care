package com.child.service.contactInfo;

import com.child.dto.ContactInfoDto;
import com.child.model.ContactInfo;

import java.util.List;
import java.util.Optional;

public interface ContactInfoService {
    public Optional<ContactInfo> findById(long id);

    public List<ContactInfo> findAll();

    public ContactInfo create(ContactInfoDto entity);

    public ContactInfo update(ContactInfo entity);

    public void delete(ContactInfo entity);

    public void deleteById(long entityId);
}