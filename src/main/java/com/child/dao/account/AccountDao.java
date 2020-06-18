package com.child.dao.account;

import com.child.dao.IOperations;
import com.child.model.Account;

import java.util.Optional;

public interface AccountDao extends IOperations<Account> {
    Optional<Account> findByEmail(String email);
}
