package com.packt.learning.spring.boot.d02s01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceDemo {

    public static void main(String[] args) {
        new SpringApplication(ProductServiceDemo.class).run(args);
    }
}
