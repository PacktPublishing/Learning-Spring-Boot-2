package com.packt.learning.spring.boot.d02s02.event;

public class ProductRetrievedEvent {

    private final String productName;

    public ProductRetrievedEvent(final String productName) {
        this.productName = productName;
    }

    public final String getProductName() {
        return productName;
    }
}
