package com.configIRule;

import com.cn.iRule.MyIRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRuleConfig {
    @Bean
    public IRule orderRule(){
        return new MyIRule();
    }
}
