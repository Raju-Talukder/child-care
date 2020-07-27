package com.child.service.photo;

import com.child.model.Photo;
import com.child.model.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhotoService {
    Photo savePhoto(MultipartFile image, PhotoDto photoDto) throws IOException;
    public List<Photo> getPhoto();
    public Optional<Photo> findById(Long id);
    public List<Photo> findByPath(String path);
}
