package com.child.service.child;

import com.child.dto.ChildDto;
import com.child.model.Account;
import com.child.model.Child;
import com.child.repository.ChildRepository;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ChildServiceImp implements ChildService{
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public Child createChild(MultipartFile image,ChildDto childDto) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "./photos/child/Profile";
        ChildServiceImp.photoDuplicate(image, fileName, uploadDir);
        Child child = new Child();
        child.setName(childDto.getName());
        child.setPhoto(fileName);
        child.setDateOfBirth(childDto.getDateOfBirth());
        child.setGender(childDto.getGender());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName()).get();
        if(childRepository.findById(2l).isPresent()){
            Child childes = childRepository.findById(2l).get();
            account.addChild(childes);
        }
        account.addChild(child);
        return childRepository.save(child);
    }

    public static void photoDuplicate(MultipartFile image, String fileName, String uploadDir) throws IOException {
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
    }

    @Override
    public Optional<Child> findById(Long id) {
        return childRepository.findById(id);
    }

    @Override
    public List<Child> findAll() {
        return childRepository.findAll();
    }

    @Override
    public void deleteById(long entityId) {
        childRepository.deleteById(entityId);
    }

    @Override
    public List<Child> findAlByParents(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByEmail(authentication.getName()).get();
        return childRepository.findChildByAccount(account);
    }

    @Override
    public void deleteChild(Long id) {
        Child child = childRepository.findById(id).get();
        childRepository.delete(child);
    }
}
