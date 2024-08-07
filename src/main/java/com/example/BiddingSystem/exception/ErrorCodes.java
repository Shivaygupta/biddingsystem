package com.example.BiddingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    FIELD_VALIDATION_ERROR(1001),
    BUSINESS_VALIDATION_ERROR(1002),
    FORBIDDEN_ERROR(1003),
    UNAUTHORIZED_ERROR(1004),
    NOT_FOUND(1006),
    INTERNAL_SERVER_ERROR(1009);

    private final int code;

}