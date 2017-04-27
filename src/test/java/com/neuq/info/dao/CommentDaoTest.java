package com.neuq.info.dao;

import com.neuq.info.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
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
        comment.setNickName("航了个航");
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
    @Test
    public void queryUnReadCommentByPostid()throws Exception{
        System.out.println(commentDao.queryUnReadCommentByPostid(1002l));
    }
    @Test
    public void updateCommentByIsRead()throws Exception{
        List<Long> longList =new ArrayList<Long>();
        longList.add(2l);
        longList.add(3l);
        longList.add(4l);
        System.out.println(commentDao.updateCommentByIsRead(longList));
    }


}