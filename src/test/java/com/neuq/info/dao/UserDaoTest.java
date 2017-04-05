package com.neuq.info.dao;

import com.neuq.info.dto.Page;
import com.neuq.info.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Test
    public void insertUser() throws Exception {
        User user =new User();
        user.setNickname("lihang");
        user.setCity("衡水");
        user.setAvatar("avator");
        user.setCountry("中国");
        user.setOpenid("openid");
        user.setUnionId("unionId");
        user.setGender("1");
        user.setJwPwd("111");
        user.setJwUser("111");
        user.setProvince("河北");
        userDao.insertUser(user);


    }

    @Test
    public void queryUserById() throws Exception {
        User user =userDao.queryUserById(1000L);
        System.out.println(user);
    }

    @Test
    public void queryAllUserByPage() throws Exception {
        Page page=new Page();
        page.setCurrentPage(1);
        page.setTotalNumber(userDao.queryAllUserCount());
        List<User> list =userDao.queryAllUserByPage(page);
        System.out.println(list.size());
    }

    @Test
    public void queryUserByOpenid() throws Exception {
        User user =userDao.queryUserByOpenid("111");
        System.out.println(user);
    }

    @Test
    public void queryUserByUnionid() throws Exception {
        User user =userDao.queryUserByUnionid("111");
        System.out.println(user);
    }

    @Test
    public void queryAllUserByGender() throws Exception {
        List<User> list =userDao.queryAllUserByGender("1");
        System.out.println(list.size());
    }

}