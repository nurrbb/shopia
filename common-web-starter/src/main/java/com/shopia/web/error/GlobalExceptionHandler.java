package com.shopia.web.error;

import com.shopia.contracts.error.ErrorCode;
import com.shopia.contracts.error.ErrorResponse;
import com.shopia.web.filter.CorrelationIdFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ShopiaException.class)
    public ResponseEntity<ErrorResponse> handleShopiaException(ShopiaException ex) {
        HttpStatus status = mapToHttpStatus(ex.getErrorCode());
        var body = new ErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                MDC.get(CorrelationIdFilter.MDC_KEY),
                Instant.now(),
                List.of()
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        var body = new ErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                "Request validation failed",
                MDC.get(CorrelationIdFilter.MDC_KEY),
                Instant.now(),
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
        log.error("Unhandled exception occurred", ex);
        var body = new ErrorResponse(
                ErrorCode.INTERNAL_ERROR,
                "An unexpected error occurred",
                MDC.get(CorrelationIdFilter.MDC_KEY),
                Instant.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private HttpStatus mapToHttpStatus(ErrorCode code) {
        return switch (code) {
            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
            case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case CATALOG_UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
            case PAYMENT_FAILED -> HttpStatus.BAD_GATEWAY;
            case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
