package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableEurekaClient 可以不用加这个注解，zuul自动整合
@EnableZuulProxy
public class AppZuul9001 {
    public static void main(String[] args) {
        SpringApplication.run(AppZuul9001.class);
    }
}
