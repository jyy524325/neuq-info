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
     * @param comment
     * @return
     */
    int insertComment(@Param("comment")Comment comment,@Param("fromUserId")long fromUserId,@Param("toUserId")long toUserId,@Param("level")int level,@Param("pCommentId")long pCommentId);

    /**
     * 根据postId查询评论
     * @param postId
     * @return
     */
    List<Comment> queryCommentByPostid(@Param("postId")long postId,@Param("level")int level,@Param("pCommentId")long pCommentId);

    /**
     * 更新评论的喜欢数量
     * @param commentId
     * @return
     */
    int updateLikeCount(@Param("commentId") long commentId, @Param("flag") int flag);
    List<Comment> queryUnReadCommentByPostid(long userId);
    int updateCommentByIsRead(List<Long> commentIdList);
    int delComment(long commentId);
    Long queryCommentUserIdByCommentId(long commentId);



}
