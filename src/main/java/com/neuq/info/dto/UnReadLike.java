package com.neuq.info.dto;

import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;

import java.util.Date;

/**
 * Created by lihang on 2017/4/27.
 */
public class UnReadLike extends UnRead{
    private Post post;
    private Like like;


    public UnReadLike(Like like, Post post, Date date) {
        this.like = like;
        this.post = post;
        this.date=date;
    }

    public Like getLike() {
        return like;
        }

public void setLike(Like like) {
        this.like = like;
        }

public Post getPost() {
        return post;
        }

public void setPost(Post post) {
        this.post = post;
        }

@Override
public String toString() {
        return "UnReadLike{" +
        "like=" + like +
        ", post=" + post +
        '}';
        }
        }