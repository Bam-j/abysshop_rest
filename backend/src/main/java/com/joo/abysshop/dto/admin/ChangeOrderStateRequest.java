package com.joo.abysshop.dto.admin;

public record ChangeOrderStateRequest(Long orderId, String newState) {

    public static ChangeOrderStateRequest of(Long orderId, String newState) {
        return new ChangeOrderStateRequest(orderId, newState);
    }
}
