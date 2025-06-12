package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

///分布式项目中，配置类还需要再在resources中的spring.factories中配置一下，spring才会扫描到
@Configuration
//这里限制了该类要有springMvc时才生效，像在网关中不使用SpringMvc就不生效，避免了找不到包的报错！！
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里配置（new了一个）UserInfoInterceptor拦截器，这样才会生效
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
