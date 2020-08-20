package com.child.service.account;

import com.child.dto.*;
import com.child.model.Account;
import com.child.model.Child;
import com.child.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createMember(AccountCreateDto accountDto) throws Exception;

    Account createUserByAdmin(AccountCreateDto accountDto);

    void verifyCode(CodeVerifyDto codeVerifyDto);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    List<Account> findAll();

    Account update(MultipartFile image,AccountUpdateDto accountUpdateDto) throws IOException;

    void delete(Long id);
}
