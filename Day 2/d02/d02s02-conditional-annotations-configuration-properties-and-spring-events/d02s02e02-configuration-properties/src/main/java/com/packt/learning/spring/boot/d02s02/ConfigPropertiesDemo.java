package com.packt.learning.spring.boot.d02s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot class which demos the usage of a {@link org.springframework.boot.context.properties.ConfigurationProperties} class
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class ConfigPropertiesDemo {

    public static void main(String[] args) {
        new SpringApplication(ConfigPropertiesDemo.class).run(args);
    }
}
