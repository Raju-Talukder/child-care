package com.child.repository;

import com.child.model.Account;
import com.child.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);
//    public List<Account> findAllByRoles(Set<Role> roles);
//    public List<Account> findAccountsByRoles(Set<Role> roles);
}
