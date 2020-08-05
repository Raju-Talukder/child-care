package com.child.service.account;

import com.child.dao.account.AccountDao;
import com.child.dao.address.AddressDao;
import com.child.dao.photo.PhotoDao;
import com.child.dao.verifyAccount.VerifyAccountDao;
import com.child.dto.AccountCreateDto;
import com.child.dto.AccountUpdateDto;
import com.child.dto.CodeVerifyDto;
import com.child.dto.PhotoDto;
import com.child.mail.Mail;
import com.child.mail.MailService;
import com.child.model.*;
import com.child.service.role.RoleService;
import com.child.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PhotoDao photoDao;

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
        addressDao.create(add);
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
        addressDao.create(add);
        account.addAddress(add);

        if(roleService.findById(2l).isPresent()) {
            Role role = roleService.findById(2l).get();
            account.addRole(role);
        }
        return accountDao.create(account);
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

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account update(AccountUpdateDto accountUpdateDto) {
        Account account = new Account();
        account.setFirstName(accountUpdateDto.getFirstName());
        account.setLastName(accountUpdateDto.getLastName());

        Address add = new Address();
        add.setAddress(accountUpdateDto.getAddress());
        add.setCity(accountUpdateDto.getCity());
        add.setZip(accountUpdateDto.getZip());
        addressDao.update(add);

        return accountDao.update(account);
    }

    @Override
    public Photo savePhoto(MultipartFile image, PhotoDto photoDto) throws IOException {
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String uploadDir = "./photos/Profile";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);
        try{
            InputStream inputStream = image.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Not found");
        }
        Photo photo = new Photo();
        photo.setName(fileName);
        photo.setPath("Profile");
        return photoDao.create(photo);
    }

    @Override
    public void delete(Long id) {
        Account account = accountDao.findById(id).get();
        accountDao.delete(account);
        Address address = addressDao.findById(id).get();
        addressDao.delete(address);
    }
}
