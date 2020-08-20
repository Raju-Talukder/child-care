package com.child.service.child;

import com.child.dto.ChildDto;
import com.child.model.Child;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ChildService {
    Child createChild(MultipartFile image, ChildDto childDto) throws IOException;
    Optional<Child> findById(Long id);
    List<Child> findAll();
    void deleteById(long entityId);
    List<Child> findAlByParents();
    void deleteChild(Long id);
}
