package com.child.dao.account;

import com.child.model.Account;
import com.child.model.Role;
import com.child.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class AccountDaoImp implements AccountDao{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account update(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        accountRepository.deleteById(entityId);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

//    @Override
//    public List<Account> findAccountsByRoles(Set<Role> roles) {
//        return accountRepository.findAccountsByRoles(roles);
//    }
//
//    @Override
//    public List<Account> findAllByRoles(Set<Role> roles) {
//        return accountRepository.findAllByRoles(roles);
//    }
}
