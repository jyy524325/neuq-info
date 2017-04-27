package com.neuq.info.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml","classpath:spring/spring-web.xml"})
public class CommentServiceTest {
    @Resource
    private CommentService commentService;
    @Test
    public void queryComment() throws Exception {
        System.out.println(commentService.queryComment(2l));
    }

    @Test
    public void addComment() throws Exception {
        commentService.addComment("web 测试",1023,48);
    }

}