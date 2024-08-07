package com.example.BiddingSystem.exception;

public class BidException extends CustomException {
    public BidException(int status, String message, String errorCode) {
        super(status, message, errorCode);
    }

    public BidException(int status, String message) {
        super(status, message);
    }

    public BidException(int status) {
        super(status);
    }
}
