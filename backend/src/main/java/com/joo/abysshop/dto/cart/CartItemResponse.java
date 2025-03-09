package com.joo.abysshop.dto.cart;

public record CartItemResponse(
    Long cartItemId,
    Long cartId,
    Long productId,
    String productName,
    Long totalPrice,
    Long totalQuantity) {

    public static CartItemResponse of(
        Long cartItemId,
        Long cartId,
        Long productId,
        String productName,
        Long totalPrice,
        Long totalQuantity
    ) {
        return new CartItemResponse(cartItemId, cartId, productId, productName,
            totalPrice, totalQuantity);
    }
}
