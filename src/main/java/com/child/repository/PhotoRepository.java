package com.child.repository;

import com.child.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    public List<Photo> findPhotosByPath(String path);
}
