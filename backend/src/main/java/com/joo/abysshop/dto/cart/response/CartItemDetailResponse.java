package com.joo.abysshop.dto.cart.response;

public record CartItemDetailResponse(
    Long quantity,
    Long price
) {

    public CartItemDetailResponse(Long quantity, Long price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Long getTotalPrice() {
        return this.price * quantity;
    }
}
