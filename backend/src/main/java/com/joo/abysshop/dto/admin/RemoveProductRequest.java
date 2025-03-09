package com.joo.abysshop.dto.admin;

public record RemoveProductRequest(Long productId) {

    public static RemoveProductRequest of(Long productId) {
        return new RemoveProductRequest(productId);
    }
}
