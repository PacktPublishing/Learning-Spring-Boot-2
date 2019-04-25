package com.packt.learning.spring.boot.d01s02;

import com.packt.learning.spring.boot.d01s02.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * A simple demo of a Spring project to demo the usage of {@link org.springframework.context.annotation.Bean}s
 * activated by the command-line per {@link org.springframework.context.annotation.Profile}s
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class BeanProgrammaticProfilesDemo {

    private static final String PROFILE_ACTIVATION_PROPERTY = "spring.profiles.active";

    // usually passed as a '-Dspring.profiles.active=<profiles>' run argument
    public static void main(String[] args) {
        System.setProperty(PROFILE_ACTIVATION_PROPERTY, RunProfiles.DEV);

        final ConfigurableApplicationContext applicationContext = SpringApplication.run(BeanProgrammaticProfilesDemo.class);

        final ProductService productService = applicationContext.getBean(ProductService.class);
        productService.displayProducts();
    }
}
