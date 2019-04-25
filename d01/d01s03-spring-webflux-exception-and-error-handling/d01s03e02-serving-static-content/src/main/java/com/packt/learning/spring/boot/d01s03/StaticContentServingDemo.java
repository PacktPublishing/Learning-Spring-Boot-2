package com.packt.learning.spring.boot.d01s03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A small Spring Boot demo used to showcase serving static content from the embedded Tomcat
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class StaticContentServingDemo {

    public static void main(String[] args) {
        SpringApplication.run(StaticContentServingDemo.class, args);
    }
}
