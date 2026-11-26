package com.shopia.contracts.event;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record OrderItemPayload(
        UUID productId,
        int quantity,
        BigDecimal unitPrice
) {
    public OrderItemPayload {
        Objects.requireNonNull(productId, "productId cannot be null");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("unitPrice cannot be negative");
        }
    }
}
