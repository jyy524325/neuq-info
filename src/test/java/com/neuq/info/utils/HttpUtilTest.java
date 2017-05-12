package com.neuq.info.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuq.info.common.utils.HttpUtil;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by lihang on 2017/4/8.
 */
public class HttpUtilTest {

    HttpUtil httpUtil = new HttpUtil();

    @Test
    public void httpPost() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // Jackson提供一个树节点被称为"JsonNode",ObjectMapper提供方法来读json作为树的JsonNode根节点
        JsonNode node = mapper.readTree("{\n" +
                "        \"status\": \"ok\",\n" +
                "        \"number\": \"6\",\n" +
                "        \"xzhou\": [\n" +
                "             1491840014 , 1491753631 , 1491667205 , 1491580847 , 1491499710 , 1491486167  \n" +
                "        ],\n" +
                "        \"yzhou\": [\n" +
                "             36 , 212 , 202 , 207 , 193 , 102  \n" +
                "        ]\n" +
                "    }");
        // 看看根节点的类型
        System.out.println("node JsonNodeType:" + node.getNodeType());
        // 是不是一个容器
        System.out.println("node is container Node ? " + node.isContainerNode());
        // 得到所有node节点的子节点名称
        System.out.println("---------得到所有node节点的子节点名称-------------------------");
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.print(fieldName + " ");
        }
        System.out.println();
        System.out.println("---------得到所有node节点的子节点名称和值-------------------------");

        Iterator<Map.Entry<String, JsonNode>> all = node.fields();
        while (all.hasNext()) {
            Map.Entry i = all.next();
            System.out.println(i.getKey());
            System.out.println(i.getValue());
        }
        Map map = mapper.readValue("{\n" +
                "        \"status\": \"ok\",\n" +
                "        \"number\": \"6\",\n" +
                "        \"xzhou\": [\n" +
                "             1491840014 , 1491753631 , 1491667205 , 1491580847 , 1491499710 , 1491486167  \n" +
                "        ],\n" +
                "        \"yzhou\": [\n" +
                "             36 , 212 , 202 , 207 , 193 , 102  \n" +
                "        ]\n" +
                "    }", Map.class);
        System.out.println(map.toString());
        System.out.println((map.get("xzhou")).getClass());
//        String re=HttpUtil.sendPost("http://www.dragongod.cn/qd/temp/pandect.php","type=pan");
//        String re= HttpUtil.httpPost("http://www.dragongod.cn/qd/temp/pandect.php","type=pan");
//        System.out.println(re);
    }

    @Test
    public void get() throws Exception {

        String re = HttpUtil.sendGet("http://www.dragongod.cn/qd/temp/user_form.php", "sort=seri_1");
//                   httpUtil.httpGet("http://www.dragongod.cn/temp/user_form.php?sort=seri_1");
        System.out.println(re);
    }

}