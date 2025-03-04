package com.joo.abysshop.util.enums;

public enum OrderState {
    SHIPPING,
    COMPLETED,
    REFUNDED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
