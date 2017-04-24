package com.neuq.info.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.common.constant.WxAuth;
import com.neuq.info.common.utils.HttpUtil;
import com.neuq.info.dao.RedisDao;
import com.neuq.info.service.WxService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MapFactoryBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.neuq.info.common.constant.WxAuth.appId;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Created by lihang on 2017/4/13.
 */
@Service
public class WxServiceImpl implements WxService {
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HashMap<String,String> wxConfig;
    public Map<String, Object> getWxSession(String wxCode) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(wxConfig.get("appId"));
        sb.append("&secret=").append(wxConfig.get("secret"));
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(wxConfig.get("grantType"));
        String res = HttpUtil.sendGet((String) wxConfig.get("sessionHost"), sb.toString());
        if(res == null || res.equals("")){
            return null;
        }
        try {
            return new ObjectMapper().readValue(res, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);
        String res=redisDao.put(thirdSessionKey, expires, sb.toString());
        System.out.println(res);
        return thirdSessionKey;
    }
}
