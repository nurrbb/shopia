package com.shopia.contracts.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record PaymentSucceeded(
        UUID eventId,
        UUID orderId,
        UUID paymentId,
        BigDecimal paidAmount,
        Instant occurredAt
) {
    public PaymentSucceeded {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(paymentId, "paymentId cannot be null");
        if (paidAmount == null || paidAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("paidAmount must be positive");
        }
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
