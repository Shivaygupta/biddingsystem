package com.example.BiddingSystem.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDetails {
    private final List<FieldError> fieldErrors;
}
