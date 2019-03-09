package com.cn;

import com.configIRule.OrderRuleConfig;
import com.configIRule.PowerRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@RibbonClients({
        //调用不同的微服务，使用不同的负载均衡算法
        @RibbonClient(name = "server-power",configuration= PowerRuleConfig.class),
        @RibbonClient(name = "server-order",configuration = OrderRuleConfig.class)
       })
@EnableFeignClients
@EnableHystrix
public class AppUserCient {
    public static void main(String[] args) {
        SpringApplication.run(AppUserCient.class);
    }
}
