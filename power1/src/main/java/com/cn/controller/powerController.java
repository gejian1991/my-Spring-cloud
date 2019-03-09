package com.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class powerController {
    @Autowired
    public RestTemplate restTemplate;


    @RequestMapping("/getPower")
    public Object getPower(String name)throws Exception{
        Map<String,Object> map=new HashMap<>();
        map.put("key","power数据1");
       /* if (name==null){
            //模拟报错
            throw  new Exception();
        }*/
        //设置超时
        //Thread.sleep(8000);
        return map;
    }
}
