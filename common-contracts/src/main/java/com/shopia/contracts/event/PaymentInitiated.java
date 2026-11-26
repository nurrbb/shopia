package com.shopia.contracts.event;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record PaymentInitiated(
        UUID eventId,
        UUID orderId,
        UUID paymentId,
        String checkoutUrl,
        Instant occurredAt
) {
    public PaymentInitiated {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(paymentId, "paymentId cannot be null");
        if (checkoutUrl == null || checkoutUrl.isBlank()) {
            throw new IllegalArgumentException("checkoutUrl cannot be blank");
        }
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
