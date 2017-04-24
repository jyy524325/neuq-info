package com.neuq.info.service;

import com.neuq.info.dao.UserDao;
import com.neuq.info.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lihang on 2017/4/23.
 */
public interface UserService {
    public User queryUserByOpenId(String openid);
    public int updateUser(User user);
    public int insertUser(User user);
    public User decodeUserInfo(String encryptedData,String iv,String sessionKey);


}
