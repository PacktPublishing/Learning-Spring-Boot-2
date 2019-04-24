package com.packt.learning.spring.boot.d02s04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo for packaging and running the app in JAR format
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class JarPackagingDemo {

    public static void main(String[] args) {
        SpringApplication.run(JarPackagingDemo.class, args);
    }
}
