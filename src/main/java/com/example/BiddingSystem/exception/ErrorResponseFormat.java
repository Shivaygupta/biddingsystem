package com.example.BiddingSystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseFormat {

    public final String code;

    public final String message;

    public final int status;

    public final String timestamp;

    private final ErrorDetails errorDetails;
}