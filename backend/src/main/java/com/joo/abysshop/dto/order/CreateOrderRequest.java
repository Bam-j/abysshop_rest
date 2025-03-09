package com.joo.abysshop.dto.order;

public record CreateOrderRequest(Long cartId, Long userId) {

    public static CreateOrderRequest of(Long cartId, Long userId) {
        return new CreateOrderRequest(cartId, userId);
    }
}
