package com.child.dao.photo;

import com.child.dao.IOperations;
import com.child.model.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoDao extends IOperations<Photo> {
    public List<Photo> findByPath(String path);
}
