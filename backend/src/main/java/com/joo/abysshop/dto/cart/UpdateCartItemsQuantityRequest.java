package com.joo.abysshop.dto.cart;

public record UpdateCartItemsQuantityRequest(Long productId, Integer quantity) {

    public static UpdateCartItemsQuantityRequest of(Long productId, Integer quantity) {
        return new UpdateCartItemsQuantityRequest(productId, quantity);
    }
}
