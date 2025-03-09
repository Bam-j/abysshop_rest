package com.joo.abysshop.dto.cart;

public record RemoveItemRequest(Long cartId, Long productId, Long userId) {

    public static RemoveItemRequest of(Long cartId, Long productId, Long userId) {
        return new RemoveItemRequest(cartId, productId, userId);
    }
}
