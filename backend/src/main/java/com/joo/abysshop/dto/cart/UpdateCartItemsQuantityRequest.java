package com.joo.abysshop.dto.cart;

public record UpdateCartItemsQuantityRequest(Long productId, Long quantity) {

    public static UpdateCartItemsQuantityRequest of(Long productId, Long quantity) {
        return new UpdateCartItemsQuantityRequest(productId, quantity);
    }
}
