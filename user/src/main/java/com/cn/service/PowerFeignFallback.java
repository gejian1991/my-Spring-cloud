package com.cn.service;

import com.cn.util.R;
import org.springframework.stereotype.Component;

//降级方法与原方法一样，包括返回值
@Component//一定要加
public class PowerFeignFallback implements PowerFeignClient {
    @Override
    public Object getPower() {
        return R.error("power服务暂时不可用");
    }
}
