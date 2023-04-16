package com.yinjunbiao.entity;

import com.mysql.cj.jdbc.Blob;

/**
 * @author yinjunbiao
 * 用户pojo
 */
public class User {
    private Integer id;

    private Integer identify;

    private String phone;

    private Blob headshot;

    private String userName;

    private String address;

    private String password;

    private Integer isprivate;

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
                ", isprivate=" + isprivate +
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

    public Blob getHeadshot() {
        return headshot;
    }

    public void setHeadshot(Blob headshot) {
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

    public Integer getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Integer isprivate) {
        this.isprivate = isprivate;
    }

    public User() {
    }

    public User(Integer id, Integer identify, String phone, Blob headshot, String userName, String address, String password, Integer isprivate) {
        this.id = id;
        this.identify = identify;
        this.phone = phone;
        this.headshot = headshot;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.isprivate = isprivate;
    }
}
