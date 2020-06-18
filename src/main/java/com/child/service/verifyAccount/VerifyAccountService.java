package com.child.service.verifyAccount;

import com.child.model.VerifyAccount;

import java.util.Optional;

public interface VerifyAccountService {
    VerifyAccount create(VerifyAccount verifyAccount);
    Optional<VerifyAccount> findByToken(String token);
    Optional<VerifyAccount> findById(Long id);
}
