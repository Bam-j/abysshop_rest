package com.joo.abysshop.dto.cart;

public record CartResponse(Long cartId, Long userId, Long quantity, Long totalPoints) {

    public static CartResponse of(Long cartId, Long userId, Long quantity, Long totalPoints) {
        return new CartResponse(cartId, userId, quantity, totalPoints);
    }
}
