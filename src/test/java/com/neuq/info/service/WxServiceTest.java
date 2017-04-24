package com.neuq.info.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class WxServiceTest {
    @Autowired
    private WxService wxService;
    @Test
    public void getWxSession() throws Exception {
        Map map=wxService.getWxSession("051EaSgb2QXXlS0SOujb2KVBgb2EaSgN");
        System.out.println(map);
    }

    @Test
    public void create3rdSession() throws Exception {
       String s= wxService.create3rdSession("oCC_80BgpK_JZy06GIcy3cAUQnNM","rJp/CUcAPHPkTtYHtQk/WQ==",7200L);
        System.out.println(s);
    }

}