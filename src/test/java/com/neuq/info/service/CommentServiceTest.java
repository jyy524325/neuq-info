package com.neuq.info.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;
import static org.junit.Assert.*;

/**
 * Created by lihang on 2017/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml","classpath:spring/spring-web.xml"})
public class CommentServiceTest {
    @Resource
    private CommentService commentService;
    @Test
    public void queryComment() throws Exception {
//        System.out.println(commentService.queryComment(1l));
        ObjectMapper objectMapper =new ObjectMapper();
        String re=objectMapper.writeValueAsString(commentService.queryComment(3l));
        System.out.println(re);
    }

    @Test
    public void addComment() throws Exception {
        commentService.addComment("测试",1002,3,1,0,1002);
//        commentService.addComment("web 测试",1023,48);
    }

    @Test
    public void testTime() throws Exception {

        Calendar now = Calendar.getInstance();
        int nowYear= now.get(Calendar.YEAR);
        int nowMonth= now.get(Calendar.MONTH) + 1;
        int nowDay= now.get(Calendar.DAY_OF_MONTH);
        int nowHour= now.get(Calendar.HOUR_OF_DAY);
        int nowMinute= now.get(Calendar.MINUTE);
        int nowSecond= now.get(Calendar.SECOND);
        Calendar post=Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        // 指定一个日期
        Date date = dateFormat.parse("2017-05-09 15:03:16");
//        2017-05-09 15:59:39

        post.setTime(date);
        int postYear= post.get(Calendar.YEAR);
        int postMonth= post.get(Calendar.MONTH) + 1;
        int postDay= post.get(Calendar.DAY_OF_MONTH);
        int postHour= post.get(Calendar.HOUR_OF_DAY);
        int postMinute= post.get(Calendar.MINUTE);
        int postSecond= post.get(Calendar.SECOND);
        String result;

        if(nowYear - postYear >= 1){
            result = (nowYear - postYear) + "年前";
        }else if(nowMonth - postMonth >= 1 || nowDay - postDay > 2){
            result = postMonth + "月" + postDay + "日" + " " + postHour + ":" + postMinute;
        }else if(nowDay - postDay >= 1 && nowDay - postDay <=2 ){
            result = nowDay - postDay == 1 ? ("昨天" + postHour + ":" + postMinute) : ("前天" + postHour + ":" + postMinute);
        }else if(nowHour - postHour >=1){
            result = "今天" + postHour + ":" + postMinute;
        }else if(nowMinute - postMinute >=1) {
            result = (nowMinute - postMinute) + "分钟前";
        } else if(nowSecond - postSecond >=1){
            result = (nowSecond - postSecond) + "秒前";
        }else{
            result = "刚刚";
        }
        System.out.println(result);
    }


}