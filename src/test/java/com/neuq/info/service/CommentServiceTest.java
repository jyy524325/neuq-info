package com.neuq.info.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class CommentServiceTest {
    @Resource
    private CommentService commentService;
    @Test
    public void queryComment() throws Exception {

    }

    @Test
    public void addComment() throws Exception {
        commentService.addComment("web 测试",1023,48);
    }

}