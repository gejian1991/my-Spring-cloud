package com.cn.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 更改默认访问策略，一共七种
     * 放在默认包扫描器下，所有微服务都用一种负载均衡算法，不太合适
     * 所以新建com.configIRule包，完成不同微服务，不同算法
     * @return
     */
    /*@Bean
    public IRule iRule(){
        return  new RandomRule();
        //return new RetryRule();
    }*/
    /*@Bean
    public TomcatServletWebServerFactory getTomcat(){
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
        tomcat.setPort(5000);
        return tomcat;
    }*/
}
