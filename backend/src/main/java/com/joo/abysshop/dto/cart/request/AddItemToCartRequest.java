package com.joo.abysshop.dto.cart.request;

public record AddItemToCartRequest( Long productId) {

    public static AddItemToCartRequest of(Long productId) {
        return new AddItemToCartRequest(productId);
    }
}
