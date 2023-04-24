package com.yinjunbiao.entity;


/**
 * @author yinjunbiao
 * 与数据库对应的用户
 */
public class User{
    private Integer id;

    private Integer identify;

    private String phone;

    private String  headshot;

    private String userName;

    private String address;

    private String password;

    private Integer isPrivate;

    private Integer cnt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", identify=" + identify +
                ", phone='" + phone + '\'' +
                ", headshot=" + headshot +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", isPrivate=" + isPrivate +
                ", cnt=" + cnt +
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

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public User() {
    }

    public User(Integer id, Integer identify, String phone, String headshot, String userName, String address, String password, Integer isPrivate, Integer cnt) {
        this.id = id;
        this.identify = identify;
        this.phone = phone;
        this.headshot = headshot;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.isPrivate = isPrivate;
        this.cnt = cnt;
    }
}
