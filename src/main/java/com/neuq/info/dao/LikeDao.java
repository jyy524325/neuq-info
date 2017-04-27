package com.neuq.info.dao;

import com.neuq.info.entity.Like;

import java.util.List;

/**
 * Created by lihang on 2017/4/15.
 */
public interface LikeDao {
     List<Like> queryUserLikeByUserId(long userid);
     int insertUserLike(Like like);
     int deleteUserLike(Like like);
     List<Like> queryUnReadLikeByUserId(long userid);
     int updateLikeByRead(List<Like> likeList);





}
