package com.packt.learning.spring.boot.d02s04.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A simple {@link javax.sql.DataSource} configuration, which configures the JPA repositories,
 * using the {@link EnableJpaRepositories} annotation
 *
 * @author bogdan.solga
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.packt.learning.spring.boot.d02s04.repository")
@EntityScan(basePackages = "com.packt.learning.spring.boot.jpa.model")
@EnableTransactionManagement
public class DataSourceConfig {
}
