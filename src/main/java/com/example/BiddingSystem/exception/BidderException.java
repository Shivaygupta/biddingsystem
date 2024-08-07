package com.example.BiddingSystem.exception;

import lombok.Getter;

@Getter
public class BidderException extends CustomException {

    public BidderException(int status, String message, String errorCode) {
        super(status, message, errorCode);
    }

    public BidderException(int status, String message) {
        super(status, message);
    }

    public BidderException(int status) {
        super(status);
    }
}
