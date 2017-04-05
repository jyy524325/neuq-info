package com.neuq.info.entity;

import java.util.Date;

/**
 * Created by lihang on 2017/4/2.
 */
public class User {
    private long userId;
    private Date createTime;
    private String openid;
    private String avatar;
    private String nickname ;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String unionId;
    private String jwUser;
    private String jwPwd;

    public User(long userId, Date createTime, String openid, String avatar, String nickname, String gender, String city, String province, String country, String unionId, String jwUser, String jwPwd) {
        this.userId = userId;
        this.createTime = createTime;
        this.openid = openid;
        this.avatar = avatar;
        this.nickname = nickname;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.country = country;
        this.unionId = unionId;
        this.jwUser = jwUser;
        this.jwPwd = jwPwd;
    }

    public User() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getJwUser() {
        return jwUser;
    }

    public void setJwUser(String jwUser) {
        this.jwUser = jwUser;
    }

    public String getJwPwd() {
        return jwPwd;
    }

    public void setJwPwd(String jwPwd) {
        this.jwPwd = jwPwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", createTime=" + createTime +
                ", openid='" + openid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", unionId='" + unionId + '\'' +
                ", jwUser='" + jwUser + '\'' +
                ", jwPwd='" + jwPwd + '\'' +
                '}';
    }
}
