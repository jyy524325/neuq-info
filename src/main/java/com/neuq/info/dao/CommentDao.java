package com.neuq.info.dao;

import com.neuq.info.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lihang on 2017/4/2.
 */
public interface CommentDao {
    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    int insertComment(@Param("comment") Comment comment, @Param("fromUserId") long fromUserId, @Param("toUserId") long toUserId, @Param("level") int level, @Param("pCommentId") long pCommentId);

    /**
     * 根据postId查询评论
     *
     * @param postId
     * @return
     */
    List<Comment> queryCommentByPostid(@Param("postId") long postId, @Param("level") int level, @Param("pCommentId") long pCommentId);

    /**
     * 更新评论的喜欢数量
     *
     * @param commentId
     * @return
     */
    int updateLikeCount(@Param("commentId") long commentId, @Param("flag") int flag);

    /**
     * 根据userId查询未读评论
     * @param userId
     * @return
     */
    List<Comment> queryUnReadCommentByUserId(long userId);

    /**
     * 根据userId查询未读评论
     * @param userId
     * @return
     */
    int queryUnReadCountCommentByUserId(long userId);

    /**
     * 根据commentId更新评论已读
     * @param commentIdList
     * @return
     */
    int updateCommentByIsRead(List<Long> commentIdList);

    /**
     * 根据commentId删除单条comment
     * @param commentId
     * @return
     */
    int delComment(long commentId);
    /**
     * 根据p_commentId删除comment
     * @param p_commentId
     * @return
     */
    int delCComment(long p_commentId);

    /**
     * 根据commentId删除单条comment
     * @param postId
     * @return
     */
    int delCommentByPostId(long postId);
    /**
     * 根据commentId查询comment的userId
     * @param commentId
     * @return
     */
    Long queryCommentUserIdByCommentId(long commentId);

    /**
     * 根据commnetId查comment
     * @param commentId
     * @return
     */
    Comment queryCommentByCommentId(long commentId);


}
