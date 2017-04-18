package com.neuq.info.service;

import java.util.Map;

/**
 * Created by lihang on 2017/4/13.
 */
public interface WxService {
    /**
     * 根据小程序登录返回的code获取openid和session_key
     * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161107
     * @param wxCode
     * @return
     */
    public Map<String,Object> getWxSession(String wxCode);
    /**
     * 缓存微信openId和session_key
     * @param wxOpenId		微信用户唯一标识
     * @param wxSessionKey	微信服务器会话密钥
     * @param expires		会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
     * @return
     */
    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires);


}
