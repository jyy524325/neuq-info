package com.neuq.info.utils;

import com.neuq.info.entity.User;
import org.junit.Test;

/**
 * Created by lihang on 2017/5/12.
 */
public class TestUser {

    @Test
    public void test(){
        User user=new User();
        user.setGender(1);
        user.setProvince("Qinhuangdao");
        User user1=new User();
        user1.setGender(1);
        user1.setProvince("Qinhuangdao");
        System.out.println(user.equals(user1));
    }
}
