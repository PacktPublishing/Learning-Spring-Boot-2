package com.packt.learning.spring.boot.d01s03.controller;

import com.packt.learning.spring.boot.domain.model.Product;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/one")
    public Mono<Product> oneProduct() {
        return Mono.just(new Product(20, "Pixel 3", 200));
    }

    @GetMapping("/array")
    public Flux<Product> productsFromArray() {
        return Flux.fromArray(
                new Product[] {
                        new Product(1, "Tablet", 200),
                        new Product(2, "Phone", 300)
                }
        );
    }

    @GetMapping("/many")
    public Publisher<Product> productsGenerator() {
        return Flux.<Product>generate(sink -> sink.next(new Product(5, "iSome", 200)))
                   .take(50);
    }

    @GetMapping(
            value = "/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Publisher<Product> infiniteStreamOfProducts() {
        return Flux.<Product>generate(sink -> sink.next(new Product(10, "The product for " + Instant.now(), 200)))
                   .delayElements(Duration.ofSeconds(1));
    }
}
