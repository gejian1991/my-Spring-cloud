package com.cn.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name ="server-power"/*,fallback = PowerFeignFallback.class*/)
public interface PowerFeignClient {
    @RequestMapping("/getPower")
    public Object getPower();
}
