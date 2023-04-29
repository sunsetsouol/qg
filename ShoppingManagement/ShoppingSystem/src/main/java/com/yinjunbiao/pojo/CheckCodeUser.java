package com.yinjunbiao.pojo;

public class CheckCodeUser {
    private Integer id;

    private Integer identify;

    private String phone;

    private String  headshot;

    private String userName;

    private String address;

    private String password;

    private Integer isPrivate;

    private String checkCode;

    @Override
    public String toString() {
        return "CheckCodeUser{" +
                "id=" + id +
                ", identify=" + identify +
                ", phone='" + phone + '\'' +
                ", headshot='" + headshot + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", isPrivate=" + isPrivate +
                ", checkCode='" + checkCode + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public CheckCodeUser() {
    }

    public CheckCodeUser(Integer id, Integer identify, String phone, String headshot, String userName, String address, String password, Integer isPrivate, String checkCode) {
        this.id = id;
        this.identify = identify;
        this.phone = phone;
        this.headshot = headshot;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.isPrivate = isPrivate;
        this.checkCode = checkCode;
    }
}
