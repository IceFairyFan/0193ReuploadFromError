package com.application.posapplication.model;

public class LoginModel {
    private String email;
    private String password;
    private String id;

    public LoginModel(String email, String password, String id) {
        this.email = email;
        this.password = password;
        this.id = id;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIds() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }
}




