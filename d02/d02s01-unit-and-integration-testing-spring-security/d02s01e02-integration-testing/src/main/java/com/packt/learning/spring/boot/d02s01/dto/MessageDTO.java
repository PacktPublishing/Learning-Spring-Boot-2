package com.packt.learning.spring.boot.d02s01.dto;

import java.io.Serializable;

/**
 * A simple DTO used to carry a message to the consumer
 *
 * @author bogdan.solga
 */
public class MessageDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private final String message;

    public MessageDTO(final String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }
}
