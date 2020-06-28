package com.child.dao.address;

import com.child.model.Address;
import com.child.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImp implements AddressDao{
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Optional<Address> findById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address create(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public Address update(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public void delete(Address entity) {
        addressRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        addressRepository.deleteById(entityId);
    }
}
