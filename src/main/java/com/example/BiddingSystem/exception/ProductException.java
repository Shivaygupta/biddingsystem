package com.example.BiddingSystem.exception;

import lombok.Getter;

@Getter
public class ProductException extends CustomException {

    public ProductException(int status, String message, String errorCode) {
        super(status, message, errorCode);
    }

    public ProductException(int status, String message) {
        super(status, message);
    }

    public ProductException(int status) {
        super(status);
    }
}
