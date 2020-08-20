package com.child.service.address;

import com.child.dto.AddressDto;
import com.child.model.Address;
import com.child.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address create(AddressDto addressDto) {
        Address add = new Address();
        add.setAddress(addressDto.getAddress());
        add.setCity(addressDto.getCity());
        add.setZip(addressDto.getZip());
        return addressRepository.save(add);
    }
}
