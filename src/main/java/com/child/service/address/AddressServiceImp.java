package com.child.service.address;

import com.child.dao.address.AddressDao;
import com.child.dto.AddressDto;
import com.child.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService{
    @Autowired
    private AddressDao addressDao;

    @Override
    public Optional<Address> findById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public Address create(AddressDto addressDto) {
        return null;
    }
}
