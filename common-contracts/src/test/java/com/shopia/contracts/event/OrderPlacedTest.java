package com.shopia.contracts.event;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderPlacedTest {

    @Test
    void shouldCreateValidOrderPlacedEvent() {
        var item = new OrderItemPayload(UUID.randomUUID(), 2, new BigDecimal("49.90"));
        var event = new OrderPlaced(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                new BigDecimal("99.80"),
                "TRY",
                List.of(item),
                Instant.now()
        );

        assertThat(event.items()).hasSize(1);
        assertThat(event.currency()).isEqualTo("TRY");
    }

    @Test
    void shouldRejectEmptyItems() {
        assertThatThrownBy(() -> new OrderPlaced(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                new BigDecimal("99.80"),
                "TRY",
                List.of(),
                Instant.now()
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
