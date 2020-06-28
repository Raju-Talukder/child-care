package com.child.service.child;

import com.child.dto.ChildDto;
import com.child.model.Child;

import java.util.List;
import java.util.Optional;

public interface ChildService {
    public Child createChild(ChildDto childDto);
    Optional<Child> findById(Long id);
    public List<Child> findAll();
    public void deleteById(long entityId);
}
