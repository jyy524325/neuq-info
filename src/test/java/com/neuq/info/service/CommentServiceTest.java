package com.neuq.info.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
//        System.out.println(commentService.queryComment(1l));
        ObjectMapper objectMapper =new ObjectMapper();
        String re=objectMapper.writeValueAsString(commentService.queryComment(3l));
        System.out.println(re);
    }

    @Test
    public void addComment() throws Exception {
        commentService.addComment("测试",1002,3,1,0,1002);
//        commentService.addComment("web 测试",1023,48);
    }

    @Test
    public void delComment() throws Exception {
        System.out.println(commentService.delComment(12l,0));
    }


}