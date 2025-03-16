package com.joo.abysshop.dto.cart.response;

import java.util.List;

public record CartAndItemsResponse(
    CartResponse cart,
    List<CartItemResponse> cartItems
) {

    public static CartAndItemsResponse of(CartResponse cart, List<CartItemResponse> cartItems) {
        return new CartAndItemsResponse(cart, cartItems);
    }
}
