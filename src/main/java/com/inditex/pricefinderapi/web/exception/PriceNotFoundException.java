package com.inditex.pricefinderapi.web.exception;

public class PriceNotFoundException extends RuntimeException {

        public PriceNotFoundException(String message) {
            super(message);
        }
}
