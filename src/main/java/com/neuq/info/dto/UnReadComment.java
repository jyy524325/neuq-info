package com.neuq.info.dto;

import com.neuq.info.entity.Comment;
import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;

import java.util.Date;

/**
 * Created by lihang on 2017/4/27.
 */
public class UnReadComment extends UnRead {
    private Post post;
    private Comment comment;

    public UnReadComment(Comment comment, Post post, Date date) {
        this.post = post;
        this.comment = comment;
        this.date = date;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "UnReadComment{" +
                "post=" + post +
                ", comment=" + comment +
                '}';
    }
}