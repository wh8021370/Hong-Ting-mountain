package com.paradise.common;

import com.alibaba.fastjson.JSONObject;

public class Demo {
    public static void main(String[] args) {
        Pojo p1 = new Pojo();
        p1.setId("1");
        p1.setName("p1");
        Pojo p2 =p1;
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(p1);
        Pojo p3  = JSONObject.toJavaObject(jsonObject,Pojo.class);
        System.out.println(p2);
        p1.setId("11");
        System.out.println(p2);
        System.out.println(p3);
    }
}
