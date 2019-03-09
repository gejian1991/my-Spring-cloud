package com.cn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter extends ZuulFilter {


    //四种过滤器类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //优先级，数字越小越优先
    @Override
    public int filterOrder() {
        //默认构造器之后执行，获取request_uri
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    //是否启用
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤逻辑
    @Override
    public Object run() throws ZuulException {
        //拿到请求对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(request.getRemoteAddr()+"访问了"+request.getRequestURI()+"路由后地址"+ctx.get(FilterConstants.REQUEST_URI_KEY).toString());
        /*HttpServletResponse httpServletResponse = new Response();
        ((Response) httpServletResponse).setError();
        ctx.setResponse(httpServletResponse);*/
        return false;
    }
}
