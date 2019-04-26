package com.packt.learning.spring.boot.d01s02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * A very simple {@link RestController}, which exposes a '/hello' endpoint
 *
 * @author bogdan.solga
 */
@RestController
public class SimpleRestController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/hello"
    )
    public String hello() {
        return "Hello, Spring Boot! The current time is " + LocalTime.now();
    }

    @GetMapping("/requestParams")
    public String requestParams(@RequestParam String color, @RequestParam String weight) {
        return "The color is " + color + ", the weight is " + weight;
    }

    @GetMapping("/{first}/{second}")
    public String pathVariables(@PathVariable String first, @PathVariable String second) {
        return "The first is " + first + ", the second is " + second;
    }
}
