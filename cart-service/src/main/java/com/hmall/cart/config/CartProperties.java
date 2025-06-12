package com.hmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


///这里实现nacos配置热更新!!在nacos中有对应的配置文件，
///cartService代码中，本来将购物车中商品数量写死了，但是配置后只要在service实现一个CartProperties实例，
/// 然后用这里的变量maxItems，就可以在nacos配置文件中直接改
@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}

