package com.child.service.account;

import com.child.dto.AccountCreateDto;
import com.child.dto.CodeVerifyDto;
import com.child.model.Account;

import java.util.Optional;

public interface AccountService {
    public Account createMember(AccountCreateDto accountDto) throws Exception;

    public Account createAdmin(AccountCreateDto accountDto);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    public void verifyCode(CodeVerifyDto codeVerifyDto);
}
