package com.shopia.contracts.event;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record OrderCancelled(
        UUID eventId,
        UUID orderId,
        UUID userId,
        String reason,
        Instant occurredAt
) {
    public OrderCancelled {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(userId, "userId cannot be null");
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("reason cannot be blank");
        }
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
