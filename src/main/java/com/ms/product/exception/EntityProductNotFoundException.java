package com.ms.product.exception;

public class EntityProductNotFoundException extends RuntimeException {
    public EntityProductNotFoundException(String message) {
        super(message);
    }
}
