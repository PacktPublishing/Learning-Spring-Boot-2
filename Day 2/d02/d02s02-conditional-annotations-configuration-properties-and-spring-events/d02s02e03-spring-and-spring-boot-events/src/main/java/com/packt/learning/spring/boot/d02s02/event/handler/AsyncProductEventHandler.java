package com.packt.learning.spring.boot.d02s02.event.handler;

import com.packt.learning.spring.boot.d02s02.event.ProductRetrievedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncProductEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProductEventHandler.class);

    @Async
    @EventListener(condition = "#productRetrievedEvent.productName.length() > 0")
    public void processAsyncProductEvent(final ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("Processing the product '{}' async...", productRetrievedEvent.getProductName());
    }
}
