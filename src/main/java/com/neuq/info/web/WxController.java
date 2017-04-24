package com.neuq.info.web;

import com.neuq.info.common.aes.AES;
import com.neuq.info.dao.RedisDao;
import com.neuq.info.entity.User;
import com.neuq.info.enums.ErrorStatus;
import com.neuq.info.service.UserService;
import com.neuq.info.service.WxService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by lihang on 2017/4/13.
 */
@Controller
@RequestMapping("/wx")
public class WxController {
    @Autowired
    private WxService wxService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisDao redisDao;

    /**
     * 根据客户端传过来的code从微信服务器获取appid和session_key，然后生成3rdkey返回给客户端，后续请求客户端传3rdkey来维护客户端登录态
     * @param wxCode	小程序登录时获取的code
     * @return
     */
    @RequestMapping(value = "/getSession", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String,Object> createSssion(@RequestParam(required = true,value = "code")String wxCode){
        Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);

        if(null == wxSessionMap){
            return rtnParam(ErrorStatus.communication_failure, null);
        }
        //获取异常
        if(wxSessionMap.containsKey("errcode")){
            return rtnParam(ErrorStatus.failed_get_WeChat_session_key, null);
        }
        String wxOpenId = (String)wxSessionMap.get("openid");
        String wxSessionKey = (String)wxSessionMap.get("session_key");
        System.out.println(wxSessionKey);
        Long expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
        String thirdSession = wxService.create3rdSession(wxOpenId, wxSessionKey, expires);
        Map<String, String> map = new HashMap<String, String>();
        map.put("sessionId",thirdSession);
        return rtnParam(ErrorStatus.exist, map);
    }
    /**
     * 验证用户信息完整性
     * @param rawData	微信用户基本信息
     * @param signature	数据签名
     * @param sessionId	会话ID
     * @return
     */
    @RequestMapping(value = "/checkUserInfo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String,Object> checkUserInfo(@RequestParam(required = true,value = "rawData")String rawData,
                                            @RequestParam(required = true,value = "signature")String signature,
                                            @RequestParam(required = true,defaultValue = "sessionId")String sessionId){
        Object wxSessionObj = redisDao.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(ErrorStatus.user_identity_expired, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String sessionKey = wxSessionStr.split("#")[0];
        StringBuffer sb = new StringBuffer(rawData);
        sb.append(sessionKey);

        byte[] encryData = DigestUtils.sha1(sb.toString());
        byte[] signatureData = signature.getBytes();
        Boolean checkStatus = Arrays.equals(encryData, signatureData);
        return rtnParam(ErrorStatus.exist, new HashMap().put("checkPass", checkStatus));
    }

    /**
     * 获取用户openId和unionId数据(如果没绑定微信开放平台，解密数据中不包含unionId)
     * @param encryptedData 加密数据
     * @param iv			加密算法的初始向量
     * @return
     */
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String,Object> decodeUserInfo(@RequestParam(required = true,value = "encryptedData")String encryptedData,
                                              @RequestParam(required = true,defaultValue = "iv")String iv, HttpServletRequest request){
        String sessionKey= (String)request.getAttribute("sessionKey");
        String openId= (String)request.getAttribute("openId");
        System.out.println(sessionKey);
        System.out.println(openId);
        User user=userService.queryUserByOpenId(openId);
        User user1=userService.decodeUserInfo(encryptedData,iv,sessionKey);
        if(user1==null){
            return rtnParam(ErrorStatus.user_sensitive_data_decryption_failed, null);
        }
        System.out.println(user1);
        int count=0;
        if(user==null){
            count=userService.insertUser(user1);
        }else {
            count= userService.updateUser(user1);
        }
        System.out.println(count);
        if(count!=0){
            return rtnParam(ErrorStatus.SUCCESS,null);
        }else {
            return rtnParam(ErrorStatus.user_sensitive_data_decryption_failed, null);
        }
    }

    protected Map<String,Object> rtnParam(ErrorStatus errorStatus, Object data) {
        //正常的业务逻辑
        Map<String,Object> map;
        if(errorStatus.getCode() == 0){
            map=new HashMap<String,Object>();
            map.put("code",errorStatus.getCode());
            map.put("message",errorStatus.getMessage());
            map.put("content",(data == null)? new Object() : data);
            return map;
        }else{
            map=new HashMap<String,Object>();
            map.put("code",errorStatus.getCode());
            map.put("message",errorStatus.getMessage());
            return map;
        }
    }
}
