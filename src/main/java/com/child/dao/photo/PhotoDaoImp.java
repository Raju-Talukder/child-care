package com.child.dao.photo;

import com.child.model.Photo;
import com.child.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PhotoDaoImp implements PhotoDao{
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Optional<Photo> findById(long id) {
        return photoRepository.findById(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo create(Photo entity) {
        return photoRepository.save(entity);
    }

    @Override
    public Photo update(Photo entity) {
        return photoRepository.save(entity);
    }

    @Override
    public void delete(Photo entity) {
        photoRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        photoRepository.deleteById(entityId);
    }

    @Override
    public List<Photo> findByPath(String path) {
        return photoRepository.findPhotosByPath(path);
    }
}
