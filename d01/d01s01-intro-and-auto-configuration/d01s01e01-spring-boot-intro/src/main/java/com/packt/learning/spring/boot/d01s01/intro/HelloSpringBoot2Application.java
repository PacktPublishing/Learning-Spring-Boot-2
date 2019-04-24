package com.packt.learning.spring.boot.d01s01.intro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A minimal Spring Boot 2 application
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class HelloSpringBoot2Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloSpringBoot2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBoot2Application.class);

        LOGGER.info("The application started and finished properly");
    }
}
