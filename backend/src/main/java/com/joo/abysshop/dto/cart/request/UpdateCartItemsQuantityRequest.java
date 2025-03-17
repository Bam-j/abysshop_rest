package com.joo.abysshop.dto.cart.request;

public record UpdateCartItemsQuantityRequest(Long cartId, Long productId, Long quantity) {

    public static UpdateCartItemsQuantityRequest of(Long cartId, Long productId, Long quantity) {
        return new UpdateCartItemsQuantityRequest(cartId, productId, quantity);
    }
}
