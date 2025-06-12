package com.hmall.gateway.filters;


import com.hmall.common.exception.UnauthorizedException;
import com.hmall.common.utils.CollUtils;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;  //通过它获取放行路径

    private final JwtTool jwtTool; //token校验工具

    private final AntPathMatcher antPathMatcher = new AntPathMatcher(); //spring自带的antPath匹配器

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request
        ServerHttpRequest request= exchange.getRequest();
        //2.判断是否需要拦截（是否需要登录校验）
        if(isExclude(request.getPath().toString())){
            //放行
            return chain.filter(exchange);  //执行链下一步到filter拦截器
        }
        //3.获取token
        String token=null;
        List<String> headers= request.getHeaders().get("authorization");
        if(headers!=null && !headers.isEmpty()){   //如果请求头中的token不为空
            token = headers.get(0);
        }
        //4.校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            //拦截，设置响应状态码
            ServerHttpResponse response =exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();  //终止拦截器的操作，所有拦截器在这里完结
        }
        //TODO 5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info",userInfo))
                .build();
        //6.放行
        return chain.filter(swe);
    }

    //遍历ExcludePaths来与当前路径匹配，看是否是在放行路径中
    private boolean isExclude(String path){
        for(String pathPattern : authProperties.getExcludePaths()){
            if (antPathMatcher.match(pathPattern, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
