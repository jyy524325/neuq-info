package com.neuq.info.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuq.info.common.format.CustomDateSerializer;

import java.util.Date;

/**
 * Created by lihang on 2017/4/2.
 */
@JsonRootName("post")
public class Post {
    private String avatarUrl;
    private String nickname;
    private long postId;
    private long userId;
    private String title;
    private String content;
    private Date createTime;
    private int secret;
    private int commentCount;
    private int likeCount;
    private int isSelf;
    private int isLike;

    public Post(String avatar, String nickname, long postId, long userId, String title, String content, Date createTime, int secret, int commentCount, int likeCount, int isSelf, int isLike) {
        this.avatarUrl = avatar;
        this.nickname = nickname;
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.secret = secret;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.isSelf = isSelf;
        this.isLike = isLike;
    }

    public Post() {

    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(int isSelf) {
        this.isSelf = isSelf;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Post{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", postId=" + postId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", secret=" + secret +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", isSelf=" + isSelf +
                ", isLike=" + isLike +
                '}';
    }
}


