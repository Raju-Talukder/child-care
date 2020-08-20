package com.child.exception;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> deatails;

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDeatails() {
        return deatails;
    }

    public void setDeatails(List<String> deatails) {
        this.deatails = deatails;
    }
}
