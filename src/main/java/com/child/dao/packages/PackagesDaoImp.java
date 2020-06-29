package com.child.dao.packages;

import com.child.model.Packages;
import com.child.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PackagesDaoImp implements PackagesDao{
    @Autowired
    private PackagesRepository packagesRepository;

    @Override
    public Optional<Packages> findById(long id) {
        return packagesRepository.findById(id);
    }

    @Override
    public List<Packages> findAll() {
        return packagesRepository.findAll();
    }

    @Override
    public Packages create(Packages entity) {
        return packagesRepository.save(entity);
    }

    @Override
    public Packages update(Packages entity) {
        return packagesRepository.save(entity);
    }

    @Override
    public void delete(Packages entity) {
        packagesRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        packagesRepository.deleteById(entityId);
    }
}
