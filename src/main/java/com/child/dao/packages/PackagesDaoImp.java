package com.child.dao.packages;

import com.child.model.Packages;

import java.util.List;
import java.util.Optional;

public class PackagesDaoImp implements PackagesDao{
    @Override
    public Optional<Packages> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Packages> findAll() {
        return null;
    }

    @Override
    public Packages create(Packages entity) {
        return null;
    }

    @Override
    public Packages update(Packages entity) {
        return null;
    }

    @Override
    public void delete(Packages entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}
