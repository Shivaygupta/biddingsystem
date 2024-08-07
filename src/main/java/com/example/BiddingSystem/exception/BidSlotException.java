package com.example.BiddingSystem.exception;

import lombok.Getter;

@Getter
public class BidSlotException extends CustomException {

    public BidSlotException(int status, String message, String errorCode) {
        super(status, message, errorCode);
    }

    public BidSlotException(int status, String message) {
        super(status, message);
    }

    public BidSlotException(int status) {
        super(status);
    }
}
