package com.joo.abysshop.dto.cart.request;

public record DeleteItemFromCartRequest(Long cartId, Long productId, Long userId) {

    public static DeleteItemFromCartRequest of(Long cartId, Long productId, Long userId) {
        return new DeleteItemFromCartRequest(cartId, productId, userId);
    }
}
