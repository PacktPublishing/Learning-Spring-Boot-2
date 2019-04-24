package com.packt.learning.spring.boot.d02s03.config;

import com.packt.learning.spring.boot.d02s03.model.Product;
import com.packt.learning.spring.boot.d02s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * A simple {@link javax.sql.DataSource} configuration, which:
 * <ul>
 *     <li>configures the JPA repositories, using the {@link EnableJpaRepositories} annotation</li>
 *     <li>inserts a simple {@link Product} in the auto-wired [in-memory] database</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.packt.learning.spring.boot.d02s03.repository")
public class DataSourceConfig {

    private final ProductService productService;

    @Autowired
    public DataSourceConfig(final ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        final Product product = new Product();
        product.setName("A default product");
        productService.create(product);
    }
}
