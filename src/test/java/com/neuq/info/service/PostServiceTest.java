package com.neuq.info.service;

import com.neuq.info.dao.PostDao;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostDao postDao;
    @Test
    public void insertPost() throws Exception {
        Post post =new Post();
        post.setContent("我爱你");
        post.setTitle("我也爱你");
        post.setSecret(1);
        post.setUserId(1000L);
        ResultModel resultModel=postService.insertPost(post);
        System.out.println(resultModel);
    }

    @Test
    public void queryPostByCount() throws Exception {
        ResultModel resultModel=postService.queryPostByCount(0,10);
        System.out.println(resultModel);
    }

    @Test
    public void queryPostByPage() throws Exception {
        ResultModel resultModel=postService.queryPostByPage(1);
        System.out.println(resultModel);
    }

    @Test
    public void queryAllPostCount() throws Exception {

    }

    @Test
    public void deletePost() throws Exception {

    }

    @Test
    public void updateLikeCount() throws Exception {

    }

    @Test
    public void updateCommentCount() throws Exception {

    }

}