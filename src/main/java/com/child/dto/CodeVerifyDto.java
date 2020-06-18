package com.child.dto;

import com.child.validator.verifyAccount.ValidVerifyCode;

public class CodeVerifyDto {
    @ValidVerifyCode
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
