package com.neuq.info.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.common.constant.WxAuth;
import com.neuq.info.common.utils.HttpUtil;
import com.neuq.info.service.WxService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lihang on 2017/4/13.
 */
@Service
public class WxServiceImpl implements WxService {
    public Map<String, Object> getWxSession(String wxCode) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(WxAuth.appId);
        sb.append("&secret=").append(WxAuth.secret);
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(WxAuth.grantType);
        String res = HttpUtil.sendGet(WxAuth.sessionHost, sb.toString());
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
//        redisUtil.add(thirdSessionKey, expires, sb.toString());
        return thirdSessionKey;
    }
}
