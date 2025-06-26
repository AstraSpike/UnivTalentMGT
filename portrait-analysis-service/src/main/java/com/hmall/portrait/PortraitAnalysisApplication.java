package com.hmall.portrait;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hmall.portrait.mapper")
@SpringBootApplication
public class PortraitAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortraitAnalysisApplication.class, args);
    }
} 