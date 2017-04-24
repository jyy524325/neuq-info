package com.neuq.info.dao;

import com.neuq.info.entity.Like;

import java.util.List;

/**
 * Created by lihang on 2017/4/15.
 */
public interface LikeDao {
    public List<Like> queryUserLikeByUserId(long userid);
    public int insertUserLike(Like like);
    public int deleteUserLike(Like like);


}
