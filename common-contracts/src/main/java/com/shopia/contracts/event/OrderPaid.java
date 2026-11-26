package com.shopia.contracts.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record OrderPaid(
        UUID eventId,
        UUID orderId,
        UUID userId,
        BigDecimal totalAmount,
        Instant occurredAt
) {
    public OrderPaid {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(userId, "userId cannot be null");
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("totalAmount must be positive");
        }
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
