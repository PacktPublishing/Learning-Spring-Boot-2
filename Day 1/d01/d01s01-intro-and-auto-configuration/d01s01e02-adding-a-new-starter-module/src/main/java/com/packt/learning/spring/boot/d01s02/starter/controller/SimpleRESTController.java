package com.packt.learning.spring.boot.d01s02.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRESTController {

    @GetMapping("/hello")
    public String helloSpringWeb() {
        return "Hello, Spring Web!";
    }
}
