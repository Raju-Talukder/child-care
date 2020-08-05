package com.child.service.account;

import com.child.dto.AccountCreateDto;
import com.child.dto.AccountUpdateDto;
import com.child.dto.CodeVerifyDto;
import com.child.dto.PhotoDto;
import com.child.model.Account;
import com.child.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createMember(AccountCreateDto accountDto) throws Exception;

    Photo savePhoto(MultipartFile image, PhotoDto photoDto) throws IOException;

    Account createUserByAdmin(AccountCreateDto accountDto);

    void verifyCode(CodeVerifyDto codeVerifyDto);

    Optional<Account> findByEmail(String email);

    Optional<Account> findById(Long id);

    List<Account> findAll();

    Account update(AccountUpdateDto accountUpdateDto);

    void delete(Long id);
}
