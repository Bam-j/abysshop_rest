package com.joo.abysshop.dto.cart.request;

public record UpdateQuantityRequest(Long cartId, Long userId, Long productId, String operator) {

    public static UpdateQuantityRequest of(Long cartId, Long userId, Long productId, String operator) {
        return new UpdateQuantityRequest(cartId, userId, productId, operator);
    }
}
