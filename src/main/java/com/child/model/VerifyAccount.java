package com.child.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

@Entity
public class VerifyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Type(type = "org.hibernate.type.TextType")
    private String token;
    private LocalDateTime createDate;
    private LocalDateTime expireDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setExpireDate(int mint) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, mint);
        this.expireDate = now.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireDate);
    }
}
