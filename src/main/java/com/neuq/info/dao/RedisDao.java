package com.neuq.info.dao;

import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.neuq.info.common.utils.ProtostuffUtil;
import com.neuq.info.common.utils.RedisUtil;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import static com.neuq.info.common.utils.ProtostuffUtil.deserializer;

/**
 * Created by lihang on 2017/4/13.
 */
@Repository
public class RedisDao {

    public String get(final String key){
        try {
            Jedis jedis = RedisUtil.getJedis();
            try {
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    String result = ProtostuffUtil.deserializer(bytes, String.class);
                    return result;
                }
            }finally {
                RedisUtil.returnResource(jedis);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String put(final String key, final int expires, final String value){
        try {
            Jedis jedis = RedisUtil.getJedis();
            try {
                byte[] bytes = ProtostuffUtil.serializer(value);
                String result = jedis.setex(key.getBytes(), expires, bytes);
                return result;
            }finally {
                RedisUtil.returnResource(jedis);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Jedis jedis= RedisUtil.getJedis();
        jedis.set("name1","lihang");
        System.out.println(jedis.get("name1"));
        RedisUtil.returnResource(jedis);
    }
}
