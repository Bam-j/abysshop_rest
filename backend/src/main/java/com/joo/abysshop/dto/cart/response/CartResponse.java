package com.joo.abysshop.dto.cart.response;

public record CartResponse(Long cartId, Long userId, Long totalQuantity, Long totalPoints) {

    public static CartResponse of(Long cartId, Long userId, Long totalQuantity, Long totalPoints) {
        return new CartResponse(cartId, userId, totalQuantity, totalPoints);
    }
}
