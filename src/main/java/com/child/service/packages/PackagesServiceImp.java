package com.child.service.packages;

import com.child.dao.packages.PackagesDao;
import com.child.dto.PackagesDto;
import com.child.model.Packages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackagesServiceImp implements PackagesService{
    @Autowired
    private PackagesDao packagesDao;
    @Override
    public Packages createPackages(PackagesDto packagesDto) {
        String tittle = packagesDto.getTittle();
        String description = packagesDto.getDescription();

        Packages packages = new Packages();
        packages.setTittle(tittle);
        packages.setDescription(description);
        return packagesDao.create(packages);
    }

    @Override
    public Optional<Packages> findById(Long id) {
        return packagesDao.findById(id);
    }

    @Override
    public List<Packages> findAll() {
        return packagesDao.findAll();
    }
}
