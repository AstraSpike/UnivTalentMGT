package com.hmall.team;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 班子分析服务启动类
 */
@SpringBootApplication
@MapperScan("com.hmall.team.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class TeamAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeamAnalysisApplication.class, args);
    }
} 