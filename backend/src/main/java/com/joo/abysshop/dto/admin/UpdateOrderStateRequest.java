package com.joo.abysshop.dto.admin;

public record UpdateOrderStateRequest(Long orderId, String newState) {

    public static UpdateOrderStateRequest of(Long orderId, String newState) {
        return new UpdateOrderStateRequest(orderId, newState);
    }
}
