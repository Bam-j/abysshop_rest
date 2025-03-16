package com.joo.abysshop.dto.cart.response;

import com.joo.abysshop.entity.cart.CartItem;

public record CartItemResponse(
    Long cartItemId,
    Long cartId,
    Long productId,
    String productName,
    Long price,
    Long quantity) {

    public static CartItemResponse of(CartItem cartItem) {
        return new CartItemResponse(
            cartItem.getCartItemId(),
            cartItem.getCart().getCartId(),
            cartItem.getProduct().getProductId(),
            cartItem.getProduct().getProductName(),
            cartItem.getProduct().getPrice(),
            cartItem.getQuantity()
        );
    }
}
