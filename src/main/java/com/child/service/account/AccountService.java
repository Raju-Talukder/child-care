package com.child.service.account;

import com.child.dto.AccountCreateDto;
import com.child.dto.AccountUpdateDto;
import com.child.dto.CodeVerifyDto;
import com.child.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public Account createMember(AccountCreateDto accountDto) throws Exception;

    public Account createUserByAdmin(AccountCreateDto accountDto);

    public void verifyCode(CodeVerifyDto codeVerifyDto);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    public List<Account> findAll();

    public Account update(AccountUpdateDto accountUpdateDto);

    public void delete(Long id);
}
