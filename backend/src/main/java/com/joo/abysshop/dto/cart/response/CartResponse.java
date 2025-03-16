package com.joo.abysshop.dto.cart.response;

import com.joo.abysshop.entity.cart.Cart;

public record CartResponse(Long cartId, Long userId, Long totalQuantity, Long totalPoints) {

    public static CartResponse of(Cart cart) {
        return new CartResponse(
            cart.getCartId(),
            cart.getUser().getUserId(),
            cart.getTotalQuantity(),
            cart.getTotalPrice()
        );
    }
}
