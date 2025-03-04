package com.joo.abysshop.util.enums;

public enum RechargeRequestState {
    PENDING_PAYMENT,
    PENDING_POINT_DEPOSIT,
    COMPLETED,
    REFUNDED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
