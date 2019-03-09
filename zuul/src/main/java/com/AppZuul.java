package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
//@EnableEurekaClient 可以不用加这个注解，zuul自动整合
@EnableZuulProxy
public class AppZuul {
    public static void main(String[] args) {
        SpringApplication.run(AppZuul.class);
    }
}
