package com.child.dao.account;

import com.child.model.Account;
import com.child.repository.AcountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDaoImp implements AccountDao{
    @Autowired
    private AcountRepository acountRepository;

    @Override
    public Optional<Account> findById(long id) {
        return acountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return acountRepository.findAll();
    }

    @Override
    public Account create(Account entity) {
        return acountRepository.save(entity);
    }

    @Override
    public Account update(Account entity) {
        return acountRepository.save(entity);
    }

    @Override
    public void delete(Account entity) {
        acountRepository.delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        acountRepository.deleteById(entityId);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return Optional.empty();
    }
}
