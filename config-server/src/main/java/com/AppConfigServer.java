package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//声明是一个配置中心
@EnableConfigServer
@EnableEurekaClient
public class AppConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigServer.class);
    }
}
