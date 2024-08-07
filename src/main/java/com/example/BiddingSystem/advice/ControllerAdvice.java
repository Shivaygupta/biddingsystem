package com.example.BiddingSystem.advice;

import com.example.BiddingSystem.exception.CustomException;
import com.example.BiddingSystem.exception.ErrorDetails;
import com.example.BiddingSystem.exception.ErrorResponseFormat;
import com.example.BiddingSystem.exception.FieldError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.BiddingSystem.constants.CommonConstants.*;
import static com.example.BiddingSystem.exception.ErrorCodes.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String GENERIC_EXCEPTION_CODE = String.join(ERROR_CODE_DELIMITER,
            SERVICE_TOP_CONTEXT, String.valueOf(INTERNAL_SERVER_ERROR.getCode()));
    private static final String ERROR_MESSAGE = "Error due to: {}";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("MethodArgumentNotValidException occurred: {}", ex.getMessage());

        List<FieldError> fieldErrors = ex.getFieldErrors().stream()
                .map(error -> new FieldError(error.getField(), error.getDefaultMessage(), error.getCode()))
                .toList();

        ErrorResponseFormat errorResponseFormat = ErrorResponseFormat.builder()
                .code(FIELD_ERROR_CODE)
                .message(FIELD_ERROR_MESSAGE)
                .status(status.value())
                .errorDetails(ErrorDetails.builder().fieldErrors(fieldErrors).build())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
        return new ResponseEntity<>(errorResponseFormat, status);
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        log.error("InvalidBid Exception occurred: {}", e.getMessage());
        ErrorResponseFormat errorResponse = ErrorResponseFormat.builder()
                .code(e.getErrorCode()) // Assuming ProductException includes a code
                .message(e.getMessage())// Assuming there's a detailed message
                .status(e.getStatus())
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatus()));
    }
}
