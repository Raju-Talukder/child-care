package com.child.dao.verifyAccount;

import com.child.model.VerifyAccount;

import java.util.Optional;

public interface VerifyAccountDao {
    VerifyAccount create(VerifyAccount verifyAccount);
    Optional<VerifyAccount> findByToken(String token);
    Optional<VerifyAccount> findById(Long id);
}
