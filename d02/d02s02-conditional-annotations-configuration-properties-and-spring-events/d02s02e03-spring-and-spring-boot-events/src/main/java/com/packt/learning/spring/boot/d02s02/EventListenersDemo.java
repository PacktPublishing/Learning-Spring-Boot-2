package com.packt.learning.spring.boot.d02s02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot class which demos the usage of several {@link org.springframework.context.event.EventListener}
 *
 * @author bogdan.solga
 */
@SpringBootApplication
public class EventListenersDemo {

    public static void main(String[] args) {
        new SpringApplication(EventListenersDemo.class).run(args);
    }
}
