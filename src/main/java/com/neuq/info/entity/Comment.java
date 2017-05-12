package com.neuq.info.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuq.info.common.format.CustomDateSerializer;

import java.util.Date;
import java.util.List;

/**
 * Created by lihang on 2017/4/2.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Comment {

    private long commentId;
    private long postId;
    private long userId;
    private int likeCount;
    private Date createTime;
    private String content;
    private int level;
    private long pCommentId;
    private User fromUser;
    private User toUser;
    private int floor;
    private int cCommentsSize;
    private List<Comment> cComments;
    private int isSelf;

    public Comment(long postId, String content) {
        this.postId = postId;
        this.content = content;
    }

    public Comment() {

    }

    public int getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(int isSelf) {
        this.isSelf = isSelf;
    }

    public int getcCommentsSize() {
        return cCommentsSize;
    }

    public void setcCommentsSize(int cCommentsSize) {
        this.cCommentsSize = cCommentsSize;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
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


    public int getLevel() {return level;}

    public void setLevel(int level) {
        this.level = level;
    }

    public long getpCommentId() {
        return pCommentId;
    }

    public void setpCommentId(long pCommentId) {
        this.pCommentId = pCommentId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public List<Comment> getcComments() {
        return cComments;
    }

    public void setcComments(List<Comment> cComments) {
        this.cComments = cComments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", likeCount=" + likeCount +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", pCommentId=" + pCommentId +
                ", fromUser=" + fromUser +
                ", toUser=" + toUser +
                ", floor=" + floor +
                ", cCommentsSize=" + cCommentsSize +
                ", cComments=" + cComments +
                ", isSelf=" + isSelf +
                '}';
    }
}
