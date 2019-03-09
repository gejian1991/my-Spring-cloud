package com.cn.controller;

import com.cn.service.PowerFeignClient;
import com.cn.util.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    /**
    spring提供用于不同系统间通信
     */
    @Autowired
    RestTemplate restTemplate;

    //通过ribbon调用
    private static final String POWER_URL="http://SERVER-POWER";
    private static final String ORDER_URL="http://server-order";

    @Autowired
    private PowerFeignClient powerFeignClient;

    @RequestMapping("/getUser.do")
    public R getUser(){
        Map<String,Object> map=new HashMap<>();
        map.put("key","用户数据");
        return R.success("返回成功",map);
    }

    /**
     * 痛过Feign进行调用
     * @return
     */
    @RequestMapping("/getFeignPower.do")
    //可以设置threadPoolKey，单个线程池，或者一组线程池共享的一个线程池groupKey
   @HystrixCommand(fallbackMethod = "getFeignPowerFullBack")
    public R getFeignPower(){
        System.out.println("调用了方法");
        return R.success("操作成功",powerFeignClient.getPower());
    }

    @RequestMapping("/getPower.do")
    //可以设置threadPoolKey，单个线程池，或者一组线程池共享的一个线程池groupKey
    @HystrixCommand(fallbackMethod = "getFeignPowerFullBack",threadPoolKey = "power",/*设置限流，线程属性的key*/   //,commandKey = "power",/*命名属性的key，对应hystrix的command*/
            groupKey = "power",threadPoolProperties ={@HystrixProperty(name = "coreSize",value = "5")})//降级的方法（一），比较麻烦
    public R getPower(){
        System.out.println("调用了方法");
        return R.success("操作成功",/*restTemplate.getForObject(POWER_URL+"/getPower",R.class)*/powerFeignClient.getPower());
    }

    @RequestMapping("/getOrder.do")
    public R getOrder(){
        return R.success("操作成功",restTemplate.getForObject(ORDER_URL+"/getOrder.do",R.class));
    }

    public R getFeignPowerFullBack(){
        return R.error("返回降级方法，稍后重试");
    }
}
