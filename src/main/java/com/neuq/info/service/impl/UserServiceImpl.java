package com.neuq.info.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.common.aes.AES;
import com.neuq.info.dao.UserDao;
import com.neuq.info.entity.User;
import com.neuq.info.enums.ErrorStatus;
import com.neuq.info.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

/**
 * Created by lihang on 2017/4/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User queryUserByOpenId(String openid) {
        User user = userDao.queryUserByOpenId(openid) == null ? null : userDao.queryUserByOpenId(openid);
        return user;
    }


    public int updateUser(User user) {
        int res = userDao.updateUser(user);
        return res;
    }

    public int insertUser(User user) {
        int res = userDao.insertUser(user);
        return res;

    }

    public User decodeUserInfo(String encryptedData, String iv, String sessionKey) {
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                System.out.println(userInfo);
                User user;
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    user = objectMapper.readValue(userInfo, User.class);
                    return user;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
