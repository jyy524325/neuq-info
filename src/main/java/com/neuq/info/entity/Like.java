package com.neuq.info.entity;

import java.util.Date;

/**
 * Created by lihang on 2017/4/15.
 */
public class Like {
    private long postId;
    private long userId;
    private Date createTime;

    public Like() {
    }

    public Like(long postId, long userId, Date createTime) {
        this.postId = postId;
        this.userId = userId;
        this.createTime = createTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
