package com.joo.abysshop.dto.product.request;

public record DeleteProductRequest(Long productId) {

    public static DeleteProductRequest of(Long productId) {
        return new DeleteProductRequest(productId);
    }
}
