package com.configIRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerRuleConfig {
    @Bean
    public IRule powerRule(){
        return new RandomRule();
    }
}
