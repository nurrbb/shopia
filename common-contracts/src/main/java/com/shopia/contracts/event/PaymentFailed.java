package com.shopia.contracts.event;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record PaymentFailed(
        UUID eventId,
        UUID orderId,
        UUID paymentId,
        String reason,
        Instant occurredAt
) {
    public PaymentFailed {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(paymentId, "paymentId cannot be null");
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("reason cannot be blank");
        }
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
