package com.hmall.personnel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.hmall.personnel.repository")
@EnableTransactionManagement
public class JpaConfig {
}
