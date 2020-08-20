package com.child.service.role;

import com.child.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);
    Role create(Role role);
}
