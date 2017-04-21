package com.neuq.info.entity;

import java.util.Date;

/**
 * Created by lihang on 2017/4/2.
 */
public class Comment {
    private long commentId;
    private long postId;
    private long userId;
    private String avatar;
    private int likeCount;
    private Date createTime;
    private String nickname;
    private String content;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        this.avatar = avatar;
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
                ", avatar='" + avatar + '\'' +
                ", likeCount=" + likeCount +
                ", createTime=" + createTime +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
