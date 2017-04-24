package com.neuq.info.dao;

import com.neuq.info.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by lihang on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CommentDaoTest {
    @Resource
    private CommentDao commentDao;
    @Test
    public void insertComment() throws Exception {
        Comment comment =new Comment();
        comment.setPostId(2L);
        comment.setUserId(1000L);
        comment.setContent("评论哦");
        comment.setAvatarUrl("avator");
        comment.setNickname("航了个航");
    commentDao.insertComment(comment);
    }

    @Test
    public void queryCommentByPostid() throws Exception {
             List<Comment>list = commentDao.queryCommentByPostid(1);
        System.out.println(list);

    }
    @Test
    public void updateLikeCount()throws Exception{
        commentDao.updateLikeCount(7,0);
    }

}