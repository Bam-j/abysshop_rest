package com.joo.abysshop.dto.cart.request;

public record AddItemToCartRequest(Long cartId, Long productId) {

    public static AddItemToCartRequest of(Long cartId, Long productId) {
        return new AddItemToCartRequest(cartId, productId);
    }
}
