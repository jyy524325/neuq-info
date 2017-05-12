package com.neuq.info.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihang on 2017/4/2.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {
    private long userId;
    private Date createTime;
    private String openId;
    private String avatarUrl;
    private String nickName;
    private String gender;
    private String city;
    private String language;
    private String province;
    private String country;
    private String unionId;
    private String jwUser;
    private String jwPwd;
    private HashMap<String, String> watermark;

    public User(long userId, Date createTime, String openId, String avatarUrl, String nickName, String gender, String city, String language, String province, String country, String unionId, String jwUser, String jwPwd, HashMap<String, String> watermark) {
        this.userId = userId;
        this.createTime = createTime;
        this.openId = openId;
        this.avatarUrl = avatarUrl;
        this.nickName = nickName;
        this.gender = gender;
        this.city = city;
        this.language = language;
        this.province = province;
        this.country = country;
        this.unionId = unionId;
        this.jwUser = jwUser;
        this.jwPwd = jwPwd;
        this.watermark = watermark;
    }

    public User() {

    }

    public HashMap<String, String> getWatermark() {
        return watermark;
    }

    public void setWatermark(HashMap<String, String> watermark) {
        this.watermark = watermark;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", createTime=" + createTime +
                ", openId='" + openId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", unionId='" + unionId + '\'' +
                ", jwUser='" + jwUser + '\'' +
                ", jwPwd='" + jwPwd + '\'' +
                ", watermark=" + watermark +
                '}';
    }
}
