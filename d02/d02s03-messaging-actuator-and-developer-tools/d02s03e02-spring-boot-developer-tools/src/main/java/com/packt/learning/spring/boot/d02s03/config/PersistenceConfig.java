package com.packt.learning.spring.boot.d02s03.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.packt.learning.spring.boot.d02s03.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.packt.learning.spring.boot.jpa.model")
public class PersistenceConfig {
}
