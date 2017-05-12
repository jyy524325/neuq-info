package com.neuq.info.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.dto.UnRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml", "classpath:spring/spring-web.xml"})
public class MessageServiceTest {
    @Resource
    private MessageService messageService;

    @Test
    public void getUnReadMessageCount() throws Exception {
//        ObjectMapper objectMapper =new ObjectMapper();
//        List<UnRead> unReadList=messageService.getUnReadMessage(1002l);
//        String res=objectMapper.writeValueAsString(unReadList);
//        System.out.println(res);
//        for(UnRead unRead:unReadList){
//            System.out.println(unRead.getDate().getTime());
//        }
    }

    @Test
    public void getUnReadMessage() throws Exception {
        System.out.println(messageService.getUnReadMessageCount(1002l));
    }


}