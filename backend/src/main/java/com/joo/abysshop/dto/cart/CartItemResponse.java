package com.joo.abysshop.dto.cart;

public record CartItemResponse(
    Long cartItemId,
    Long cartId,
    Long productId,
    String productName,
    Long price,
    Long quantity) {

    public static CartItemResponse of(
        Long cartItemId,
        Long cartId,
        Long productId,
        String productName,
        Long price,
        Long quantity
    ) {
        return new CartItemResponse(
            cartItemId,
            cartId,
            productId,
            productName,
            price,
            quantity
        );
    }
}
