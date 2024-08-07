package com.example.BiddingSystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VendorException extends CustomException {
    public VendorException(int status, String message, String errorCode) {
        super(status, message, errorCode);
    }

    public VendorException(int status, String message) {
        super(status, message);
    }

    public VendorException(int status) {
        super(status);
    }
}
