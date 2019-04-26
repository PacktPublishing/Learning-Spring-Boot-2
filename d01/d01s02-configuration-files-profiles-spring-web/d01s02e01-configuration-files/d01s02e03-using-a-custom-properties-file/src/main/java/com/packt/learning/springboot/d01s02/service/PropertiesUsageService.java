package com.packt.learning.springboot.d01s02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@PropertySource("products.properties")
public class PropertiesUsageService {

    @Value("${product.description}")
    private String productDescription;

    @PostConstruct
    public void initialize() {
        System.out.println("The product description is '" + productDescription + "'");
    }
}
