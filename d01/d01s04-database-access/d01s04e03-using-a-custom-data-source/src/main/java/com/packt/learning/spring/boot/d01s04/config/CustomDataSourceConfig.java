package com.packt.learning.spring.boot.d01s04.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>configures a custom {@link javax.sql.DataSource}, using the {@link HikariDataSource} class</li>
 * </ul>
 *
 * @author bogdan.solga
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.packt.learning.spring.boot.d01s04.repository",
        transactionManagerRef = "jpaTransactionManager"
)
@EnableTransactionManagement
public class CustomDataSourceConfig {

    private static final String SCANNED_PACKAGES = "com.packt.learning.spring.boot.jpa.model";

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean
    public javax.sql.DataSource hikariConnectionPool() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName("hikari-first-connection-pool");
        hikariConfig.setMaximumPoolSize(AVAILABLE_PROCESSORS * 2);
        hikariConfig.setMinimumIdle(AVAILABLE_PROCESSORS / 2);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setMaxLifetime(120000);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public javax.sql.DataSource anotherConnectionPool() {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setPoolName("hikari-second-connection-pool");
        hikariConfig.setMaximumPoolSize(AVAILABLE_PROCESSORS * 2);
        hikariConfig.setMinimumIdle(AVAILABLE_PROCESSORS / 2);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setMaxLifetime(120000);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName + "");
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        hikariConfig.setDataSourceProperties(properties);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(SCANNED_PACKAGES);

        return entityManagerFactoryBean;
    }
}
