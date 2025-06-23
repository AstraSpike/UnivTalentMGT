package com.hmall.demand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 需求服务启动类
 */
@SpringBootApplication
@MapperScan("com.hmall.demand.mapper")
@EnableDiscoveryClient
public class DemandApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemandApplication.class, args);
    }
} 