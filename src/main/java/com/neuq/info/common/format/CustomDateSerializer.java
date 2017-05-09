package com.neuq.info.common.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lihang on 2017/4/15.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException,
                JsonProcessingException {
            Calendar now = Calendar.getInstance();
            int nowYear= now.get(Calendar.YEAR);
            int nowMonth= now.get(Calendar.MONTH) + 1;
            int nowDay= now.get(Calendar.DAY_OF_MONTH);
            int nowHour= now.get(Calendar.HOUR);
            int nowMinute= now.get(Calendar.MINUTE);
            int nowSecond= now.get(Calendar.SECOND);
            Calendar post=Calendar.getInstance();
            post.setTime(value);
            int postYear= post.get(Calendar.YEAR);
            int postMonth= post.get(Calendar.MONTH) + 1;
            int postDay= post.get(Calendar.DAY_OF_MONTH);
            int postHour= post.get(Calendar.HOUR);
            int postMinute= post.get(Calendar.MINUTE);
            int postSecond= post.get(Calendar.SECOND);
            String result;

            if(nowYear - postYear >= 1){
                result = (nowYear - postYear) + "年前";
            }else if(nowMonth - postMonth >= 1 || nowDay - postDay > 2){
                result = postMonth + "月" + postDay + "日" + " " + postHour + ":" + postMinute;
            }else if(nowDay - postDay >= 1 && nowDay - postDay <=2 ){
                result = nowDay - postDay == 1 ? ("昨天" + postHour + ":" + postMinute) : ("前天" + postHour + ":" + postMinute);
            }else if(nowHour - postHour >= 1){
                result = "今天" + postHour + ":" + postMinute;
            }else if(nowMinute - postMinute >=1) {
                result = (nowMinute - postMinute) + "分钟前";
            } else if(nowSecond - postSecond >=1){
                    result = (nowSecond - postSecond) + "秒前";
            }else{
                result = "刚刚";
            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonGenerator.writeString(result);
        }
    }
