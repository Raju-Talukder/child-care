package com.child.service.role;

import com.child.dao.role.RoleDao;
import com.child.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Optional<Role> findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role create(Role role) {
        return roleDao.create(role);
    }
}
