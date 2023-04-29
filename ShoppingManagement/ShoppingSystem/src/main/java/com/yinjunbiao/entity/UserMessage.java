package com.yinjunbiao.entity;

public class UserMessage {
    private Integer id;

    private Integer userId;

    private String message;

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserMessage() {
    }

    public UserMessage(Integer id, Integer userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
    }
}
