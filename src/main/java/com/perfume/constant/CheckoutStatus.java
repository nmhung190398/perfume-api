package com.perfume.constant;

public enum CheckoutStatus {
    ACTIVE(1),
    DELETED(0),
    DELIVERY(2),
    DONE(3);

    private final int value;

    private CheckoutStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
