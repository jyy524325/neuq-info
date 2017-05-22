package com.neuq.info.service;

import com.neuq.info.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml", "classpath:spring/spring-web.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void queryUserByOpenId() throws Exception {
        User user = userService.queryUserByOpenId("oCC_80BgpK_JZy06GIcy3cAUQnNM");
        System.out.println(user == null);
        System.out.println(user);
    }

    @Test
    public void updateUser() throws Exception {
        User user=new User();
        user.setOpenId("openid1");
        user.setAvatarUrl("111");
        user.setNickName("222");
        user.setGender(2);
        userService.updateUser(user);
    }

    @Test
    public void insertUser() throws Exception {

    }

    @Test
    public void decodeUserInfo() throws Exception {

    }

}