package com.child.service.packages;

import com.child.dto.PackagesDto;
import com.child.model.Packages;

import java.util.List;
import java.util.Optional;

public interface PackagesService {
    public Packages createPackages(PackagesDto packagesDto);
    Optional<Packages> findById(Long id);
    public List<Packages> findAll();
}
