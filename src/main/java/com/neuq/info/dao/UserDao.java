package com.neuq.info.dao;

import com.neuq.info.dto.Page;
import com.neuq.info.entity.User;

import java.util.List;

/**
 * Created by lihang on 2017/4/2.
 */
public interface UserDao {
    /**
     * 新增user
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户id查询user
     * @param userId
     * @return
     */
    User queryUserById(long userId);

    /**
     * 分页查询所有user
     * @param page
     * @return
     */
    List<User> queryAllUserByPage(Page page);

    /**
     * 根据openid查询user
     * @param openid
     * @return
     */
    User queryUserByOpenid(String openid);

    /**
     * 根据unionid查询user
     * @param unionId
     * @return
     */
    User queryUserByUnionid(String unionId);

    /**
     * 根据性别查询user
     * @param gender
     * @return
     */
    List<User> queryAllUserByGender(String gender);

    /**
     * 查询所有数量
     * @return
     */
    int queryAllUserCount();
}
