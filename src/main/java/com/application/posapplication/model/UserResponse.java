package com.application.posapplication.model;

import java.util.List;

public class UserResponse {
    private Boolean status;
    private String message;
    private List data;

    public UserResponse(){ }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List getData() {
        return data;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List data) {
        this.data = data;
    }

    // setters & getters
}
