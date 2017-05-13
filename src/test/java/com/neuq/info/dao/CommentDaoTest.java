package com.neuq.info.dao;

import com.neuq.info.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static sun.jvm.hotspot.code.CompressedStream.L;

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
        commentDao.insertComment(new Comment(3, "第一楼子评论1"), 1002, 1001, 1, 0);
    }

    @Test
    public void queryCommentByPostid() throws Exception {
        List<Comment> list = commentDao.queryCommentByPostid(1, 1, 0);
        System.out.println(list);

    }

    @Test
    public void updateLikeCount() throws Exception {
        commentDao.updateLikeCount(7, 0);
    }

    @Test
    public void queryUnReadCommentByPostid() throws Exception {
        System.out.println(commentDao.queryUnReadCommentByPostid(1002l));
    }

    @Test
    public void updateCommentByIsRead() throws Exception {
        List<Long> longList = new ArrayList<Long>();
        longList.add(2l);
        longList.add(3l);
        longList.add(4l);
        System.out.println(commentDao.updateCommentByIsRead(longList));
    }

    @Test
    public void delComment() throws Exception {
        System.out.println(commentDao.delComment(12l));
    }
    @Test
    public void queryCommentByCommentId() throws Exception {
        System.out.println(commentDao.queryCommentByCommentId(2));
    }



}