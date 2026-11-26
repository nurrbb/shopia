package com.shopia.contracts.error;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public record ErrorResponse(
        ErrorCode code,
        String message,
        String correlationId,
        Instant timestamp,
        List<String> details
) {
    public ErrorResponse {
        Objects.requireNonNull(code, "code cannot be null");
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message cannot be blank");
        }
        timestamp = timestamp != null ? timestamp : Instant.now();
        details = details != null ? List.copyOf(details) : List.of();
    }
}
