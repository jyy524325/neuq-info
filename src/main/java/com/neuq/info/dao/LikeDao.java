package com.neuq.info.dao;

import com.neuq.info.entity.Like;

import java.util.List;

/**
 * Created by lihang on 2017/4/15.
 */
public interface LikeDao {
    /**
     * 根据userId查询该用户所有点赞
     *
     * @param userId
     * @return
     */
    List<Like> queryUserLikeByUserId(long userId);

    /**
     * 插入一条点赞
     *
     * @param like
     * @return
     */
    int insertUserLike(Like like);

    /**
     * 删除一条点赞
     *
     * @param like
     * @return
     */
    int deleteUserLike(Like like);

    /**
     * 根据userId查询用户未读点赞消息
     *
     * @param userId
     * @return
     */
    List<Like> queryUnReadLikeByUserId(long userId);

    /**
     * 更新用户未读点赞为已读
     *
     * @param likeList
     * @return
     */
    int updateLikeByRead(List<Like> likeList);


}
