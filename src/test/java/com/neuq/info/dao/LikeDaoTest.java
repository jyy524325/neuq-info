package com.neuq.info.dao;

import com.neuq.info.entity.Like;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class LikeDaoTest {
    @Resource
    private LikeDao likeDao;

    @Test
    public void queryUserLikeByUserId() throws Exception {
        List<Like> result = likeDao.queryUserLikeByUserId(1000L);
        System.out.println(result);
    }

    @Test
    public void insertUserLike() throws Exception {
        Like like = new Like();
        like.setPostId(5);
        like.setUserId(1000L);
        System.out.println(likeDao.insertUserLike(like));
    }

    @Test
    public void queryUnReadLikeByUserId() throws Exception {
        List<Like> list = likeDao.queryUnReadLikeByUserId(1000l);
        System.out.println(list);
    }

    @Test
    public void updateLikeByRead() throws Exception {
        List<Like> list = likeDao.queryUnReadLikeByUserId(1000l);
        System.out.println(likeDao.updateLikeByRead(list));
    }
    @Test
    public void queryUnReadLikeCountByUserId() throws Exception {
        System.out.println(likeDao.queryUnReadLikeCountByUserId(1000));
    }

}