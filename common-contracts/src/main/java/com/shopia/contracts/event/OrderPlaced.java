package com.shopia.contracts.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record OrderPlaced(
        UUID eventId,
        UUID orderId,
        UUID userId,
        BigDecimal totalAmount,
        String currency,
        List<OrderItemPayload> items,
        Instant occurredAt
) {
    public OrderPlaced {
        Objects.requireNonNull(eventId, "eventId cannot be null");
        Objects.requireNonNull(orderId, "orderId cannot be null");
        Objects.requireNonNull(userId, "userId cannot be null");
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("totalAmount must be positive");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("currency cannot be blank");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items cannot be empty");
        }
        items = List.copyOf(items);
        occurredAt = occurredAt != null ? occurredAt : Instant.now();
    }
}
