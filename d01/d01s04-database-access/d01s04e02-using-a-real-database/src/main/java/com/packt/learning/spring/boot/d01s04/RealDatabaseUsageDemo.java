package com.packt.learning.spring.boot.d01s04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot app used to demo the usage of a local PostgreSQL database
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class RealDatabaseUsageDemo {

    public static void main(String[] args) {
        SpringApplication.run(RealDatabaseUsageDemo.class, args);
    }
}
