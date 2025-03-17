package com.joo.abysshop.dto.cart.request;

public record DeleteItemFromCartRequest(Long cartId, Long productId) {

    public static DeleteItemFromCartRequest of(Long cartId, Long productId) {
        return new DeleteItemFromCartRequest(cartId, productId);
    }
}
