package com.joo.abysshop.dto.cart;

public record AddItemRequest(Long cartId, Long productId) {

    public static AddItemRequest of(Long cartId, Long productId) {
        return new AddItemRequest(cartId, productId);
    }
}
