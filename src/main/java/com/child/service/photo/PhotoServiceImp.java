package com.child.service.photo;

import com.child.dao.photo.PhotoDao;
import com.child.model.Photo;
import com.child.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImp implements PhotoService{
    @Autowired
    private PhotoDao photoDao;

    @Override
    public Photo savePhoto(MultipartFile image, PhotoDto photoDto) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "./photos/"+photoDto.getPath();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        try{
            InputStream inputStream = image.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Not found");
        }
        Photo photo = new Photo();
        photo.setName(fileName);
        photo.setPath(photoDto.getPath());
        return photoDao.create(photo);
    }

    @Override
    public List<Photo> getPhoto() {
        return photoDao.findAll();
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return photoDao.findById(id);
    }

    @Override
    public List<Photo> findByPath(String path) {
        return photoDao.findByPath(path);
    }
}
