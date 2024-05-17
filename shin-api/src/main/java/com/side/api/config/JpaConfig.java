package com.side.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.side.db")
@EntityScan(basePackages = "com.side.db")
@Configuration
public class JpaConfig {
}
