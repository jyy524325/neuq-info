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
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";
    @Override
        public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException,
                JsonProcessingException {
//    Calendar now = Calendar.getInstance();
//            int nowYear= now.get(Calendar.YEAR);
//            int nowMonth= now.get(Calendar.MONTH) + 1;
//            int nowDay= now.get(Calendar.DAY_OF_MONTH);
//            int nowHour= now.get(Calendar.HOUR);
//            int nowMinute= now.get(Calendar.MINUTE);
//            int nowSecond= now.get(Calendar.SECOND);
//            Calendar post=Calendar.getInstance();
//            post.setTime(value);
//            int postYear= post.get(Calendar.YEAR);
//            int postMonth= post.get(Calendar.MONTH) + 1;
//            int postDay= post.get(Calendar.DAY_OF_MONTH);
//            int postHour= post.get(Calendar.HOUR);
//            int postMinute= post.get(Calendar.MINUTE);
//            int postSecond= post.get(Calendar.SECOND);
//            String result;
//
//            if(nowYear - postYear >= 1){
//                result = (nowYear - postYear) + "年前";
//            }else if(nowMonth - postMonth >= 1 || nowDay - postDay > 2){
//                result = postMonth + "月" + postDay + "日" + " " + postHour + ":" + postMinute;
//            }else if(nowDay - postDay >= 1 && nowDay - postDay <=2 ){
//                result = nowDay - postDay == 1 ? ("昨天" + postHour + ":" + postMinute) : ("前天" + postHour + ":" + postMinute);
//            }else if(nowHour - postHour >= 1){
//                result = "今天" + postHour + ":" + postMinute;
//            }else if(nowMinute - postMinute >=1) {
//                result = (nowMinute - postMinute) + "分钟前";
//            } else if(nowSecond - postSecond >=1){
//                    result = (nowSecond - postSecond) + "秒前";
//            }else{
//                result = "刚刚";
//            }
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonGenerator.writeString(format(value));
        }
    public  String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
