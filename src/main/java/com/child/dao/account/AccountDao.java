package com.child.dao.account;

import com.child.dao.IOperations;
import com.child.model.Account;
import com.child.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountDao extends IOperations<Account> {
    Optional<Account> findByEmail(String email);
//    public List<Account> findAllByRoles(Set<Role> roles);
//    public List<Account> findAccountsByRoles(Set<Role> roles);
}
