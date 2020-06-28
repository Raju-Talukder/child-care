package com.child.service.account;

import com.child.dao.account.AccountDao;
import com.child.dao.address.AddressDao;
import com.child.dao.verifyAccount.VerifyAccountDao;
import com.child.dto.AccountCreateDto;
import com.child.dto.CodeVerifyDto;
import com.child.mail.Mail;
import com.child.mail.MailService;
import com.child.model.Account;
import com.child.model.Address;
import com.child.model.Role;
import com.child.model.VerifyAccount;
import com.child.service.address.AddressService;
import com.child.service.role.RoleService;
import com.child.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MailService mailService;
    @Autowired
    private VerifyAccountDao verifyAccountDao;
    @Autowired
    private AddressDao addressDao;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account createMember(AccountCreateDto accountDto) throws Exception {
        String firstName = accountDto.getFirstName();
        String lastName = accountDto.getLastName();
        String email = accountDto.getEmail();
        String password = accountDto.getPassword();
        String city = accountDto.getCity();
        String address = accountDto.getAddress();
        String zip = accountDto.getZip();

        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPassword(password);
//        passwordEncoder.encode(password)
        account.setActive(false);

        Address add = new Address();
        add.setAddress(address);
        add.setCity(city);
        add.setZip(zip);
        addressDao.create(add);

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
        verifyAccountDao.create(verifyAccount);

        Map<String, Object> maps = new HashMap<>();
        maps.put("account", account);
        maps.put("token", token);

        Mail mail = new Mail();
        mail.setFrom("childcare@gmail.com");
        mail.setSubject("Registration");
        mail.setTo(account.getEmail());
        mail.setModel(maps);
        mailService.sendEmail(mail);

        return accountDao.create(account);
    }

    @Override
    public Account createAdmin(AccountCreateDto accountDto) {
        return null;
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountDao.findByEmail(email);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public void verifyCode(CodeVerifyDto codeVerifyDto) {
        String token = codeVerifyDto.getToken();
        VerifyAccount verifyAccount = verifyAccountDao.findByToken(token).get();
        Account account = verifyAccount.getAccount();
        account.setActive(true);
        accountDao.update(account);
    }
}
