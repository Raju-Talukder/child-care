package com.child.service.account;

import com.child.dto.AccountCreateDto;
import com.child.dto.AccountUpdateDto;
import com.child.dto.CodeVerifyDto;
import com.child.mail.Mail;
import com.child.mail.MailService;
import com.child.model.*;
import com.child.repository.AccountRepository;
import com.child.repository.AddressRepository;
import com.child.repository.VerifyAccountRepository;
import com.child.service.child.ChildServiceImp;
import com.child.service.role.RoleService;
import com.child.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private VerifyAccountRepository verifyAccountRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MailService mailService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account createMember(AccountCreateDto accountDto) throws Exception {
        Account account = new Account();
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setActive(false);

        Address add = new Address();
        add.setAddress(accountDto.getAddress());
        add.setCity(accountDto.getCity());
        add.setZip(accountDto.getZip());
        addressRepository.save(add);
        account.addAddress(add);

        if(roleService.findById(2l).isPresent()) {
            Role role = roleService.findById(2l).get();
            account.addRole(role);
        }

        String token = RandomUtils.generateRandomStringNumber(6).toUpperCase();

        VerifyAccount verifyAccount = new VerifyAccount();
        verifyAccount.setAccount(account);
        verifyAccount.setCreateDate(LocalDateTime.now());
        verifyAccount.setExpireDate(5);
        verifyAccount.setToken(token);
        verifyAccountRepository.save(verifyAccount);

        Map<String, Object> maps = new HashMap<>();
        maps.put("account", account);
        maps.put("token", token);

        Mail mail = new Mail();
        mail.setFrom("childcare@gmail.com");
        mail.setSubject("Registration");
        mail.setTo(account.getEmail());
        mail.setModel(maps);
        mailService.sendEmail(mail);

        return accountRepository.save(account);
    }

    @Override
    public Account createUserByAdmin(AccountCreateDto accountDto) {
        Account account = new Account();
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setActive(false);

        Address add = new Address();
        add.setAddress(accountDto.getAddress());
        add.setCity(accountDto.getCity());
        add.setZip(accountDto.getZip());
        addressRepository.save(add);
        account.addAddress(add);

        if(roleService.findById(2l).isPresent()) {
            Role role = roleService.findById(2l).get();
            account.addRole(role);
        }
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void verifyCode(CodeVerifyDto codeVerifyDto) {
        String token = codeVerifyDto.getToken();
        VerifyAccount verifyAccount = verifyAccountRepository.findByToken(token).get();
        Account account = verifyAccount.getAccount();
        account.setActive(true);
        accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account update(MultipartFile image,AccountUpdateDto accountUpdateDto) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "./photos/Profile";
        ChildServiceImp.photoDuplicate(image, fileName, uploadDir);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountRepository.findByEmail(authentication.getName()).get();
        account.setFirstName(accountUpdateDto.getFirstName());
        account.setLastName(accountUpdateDto.getLastName());
        account.setPhoto(fileName);

        Address add = addressRepository.findById(account.getId()).get();
        add.setAddress(accountUpdateDto.getAddress());
        add.setCity(accountUpdateDto.getCity());
        add.setZip(accountUpdateDto.getZip());
        addressRepository.save(add);

        return accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.findById(id).get();
        accountRepository.delete(account);
        Address address = addressRepository.findById(id).get();
        addressRepository.delete(address);
    }
}
