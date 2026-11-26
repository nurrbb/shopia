package com.shopia.contracts.messaging;

public final class MessagingConstants {
    public static final String EVENTS_EXCHANGE = "shopia.events";

    public static final String RK_ORDER_PLACED = "order.placed";
    public static final String RK_ORDER_PAID = "order.paid";
    public static final String RK_ORDER_CANCELLED = "order.cancelled";
    public static final String RK_PAYMENT_INITIATED = "payment.initiated";
    public static final String RK_PAYMENT_SUCCEEDED = "payment.succeeded";
    public static final String RK_PAYMENT_FAILED = "payment.failed";

    private MessagingConstants() {}
}
