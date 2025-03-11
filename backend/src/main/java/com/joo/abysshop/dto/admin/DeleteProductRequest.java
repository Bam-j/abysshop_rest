package com.joo.abysshop.dto.admin;

public record DeleteProductRequest(Long productId) {

    public static DeleteProductRequest of(Long productId) {
        return new DeleteProductRequest(productId);
    }
}
