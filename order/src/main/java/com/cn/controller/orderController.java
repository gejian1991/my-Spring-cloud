package com.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class orderController {

    @RequestMapping("/getOrder.do")
    public Object getOrder(){
        Map<String,Object> map=new HashMap<>();
        map.put("key","order数据1");
        return map;
    }
}
