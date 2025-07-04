package com.hmall.common.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

public class MqConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){    
        // 1.定义消息转换器    
         Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();    
        // 2.配置自动创建消息id，用于识别不同消息，也可以在业务中基于ID判断是否是重复消息    
         jackson2JsonMessageConverter.setCreateMessageIds(true);
         return jackson2JsonMessageConverter;
    }
}
