package com.neuq.info.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuq.info.common.format.CustomDateSerializer;

import java.util.Date;

/**
 * Created by lihang on 2017/4/2.
 */
public class Comment {
    private long commentId;
    private long postId;
    private long userId;
    private String avatarUrl;
    private String nickName;
    private int likeCount;
    private Date createTime;
    private String content;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(long commentId, long postId, long userId, String avatar, int likeCount, Date createTime) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.avatarUrl = avatar;
        this.likeCount = likeCount;
        this.createTime = createTime;
    }

    public Comment() {

    }

    public Comment(long postId, long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", likeCount=" + likeCount +
                ", createTime=" + createTime +
                ", nickname='" + nickName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
