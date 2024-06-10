package com.inditex.pricefinderapi.infrastructure.exception;

public class PriceNotFoundException extends RuntimeException {

        public PriceNotFoundException(String message) {
            super(message);
        }
}
