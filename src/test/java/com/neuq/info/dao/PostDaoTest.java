package com.neuq.info.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.dto.Page;
import com.neuq.info.entity.Post;
import com.neuq.info.entity.User;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static javafx.scene.input.KeyCode.R;
import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PostDaoTest {
    @Resource
    private PostDao postDao;

    @Test
    public void insertPost() throws Exception {
        Post post =new Post();
        post.setContent("我爱你");
        post.setTitle("我也爱你");
        post.setSecret(1);
        post.setUserId(1000L);
        int count=postDao.insertPost(post);
        System.out.println(count);

    }

    @Test
    public void queryPostByCount() throws Exception {
          List<Post> list= postDao.queryPostByCount(8,10);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(list);
        System.out.println(json);
    }

    @Test
    public void queryPostByPage() throws Exception {
        Page page=new Page();
        page.setCurrentPage(2);
        page.setTotalNumber(postDao.queryAllPostCount());
        List<Post> list= postDao.queryPostByPage(page);
        System.out.println(list);
    }
    @Test
    public void queryPostByUserId() throws Exception {
       List<Post> list= postDao.queryPostByUserId(1000l);
        System.out.println(list);
    }
    @Test
    public void queryPostByPostId() throws Exception {
        Post post= postDao.queryPostByPostId(48l);
        System.out.println(post);
    }

    @Test
    public void queryAllPost() throws Exception {

    }

    @Test
    public void deletePost() throws Exception {

    }

    @Test
    public void updateLikeCount() throws Exception {
//        int count= postDao.updateLikeCount(2,1);
        int count2= postDao.updateLikeCount(2,0);
        System.out.println(count2);

    }

    @Test
    public void updateCommentCount() throws Exception {
        postDao.updateCommentCount(2);
    }
    @Test
    public void queryPostByFirstPostId() throws Exception {
        postDao.queryPostByFirstPostId(50l);
    }


}