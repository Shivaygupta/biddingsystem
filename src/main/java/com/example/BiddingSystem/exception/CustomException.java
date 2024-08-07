package com.example.BiddingSystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final int status;
    private final String errorCode;

    // Constructor for creating an exception with status, message, and errorCode
    public CustomException(int status, String message, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    // Constructor for creating an exception with status and message
    public CustomException(int status, String message) {
        super(message);
        this.status = status;
        this.errorCode = HttpStatus.valueOf(status).name(); // Assuming HttpStatus is an enum or class that can resolve status codes to names
    }

    // Constructor for creating an exception with status only
    public CustomException(int status) {
        super(HttpStatus.valueOf(status).getReasonPhrase()); // Assuming HttpStatus provides a reason phrase for each status code
        this.status = status;
        this.errorCode = HttpStatus.valueOf(status).name();
    }
}

