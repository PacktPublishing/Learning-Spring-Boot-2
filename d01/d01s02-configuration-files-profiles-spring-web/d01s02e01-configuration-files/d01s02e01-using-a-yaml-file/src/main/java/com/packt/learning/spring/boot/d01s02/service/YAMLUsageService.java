package com.packt.learning.spring.boot.d01s02.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class YAMLUsageService {

    @Value("${custom.property}")
    private String customProperty;

    @PostConstruct
    public void initialize() {
        System.out.println("The custom value is '" + customProperty + "'");
    }
}
