package com.packt.learning.spring.boot.d02s04;

import com.packt.learning.spring.boot.d02s04.service.ProductService;
import com.packt.learning.spring.boot.jpa.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * A small Spring Boot application for testing the integration with a Spring Boot Admin server
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class MonitoredApplication {

    private static final Random RANDOM = new Random(1000);

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MonitoredApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(final ProductService productService) {
        return args -> {
            IntStream.range(0, 50)
                     .forEach(id ->  productService.create(new Product("The product #" + id, RANDOM.nextDouble() * 100)));
            LOGGER.info("The default products were successfully created!");
        };
    }
}
