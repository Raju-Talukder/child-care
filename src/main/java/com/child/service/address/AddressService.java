package com.child.service.address;

import com.child.dto.AddressDto;
import com.child.model.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> findById(Long id);
    public Address create(AddressDto addressDto);
}
