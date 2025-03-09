package com.joo.abysshop.dto.product;

public record ProductDetailResponse(
    Long productId,
    String productName,
    Long price,
    String description,
    String originalFileName
) {

    public static ProductDetailResponse of(
        Long productId,
        String productName,
        Long price,
        String description,
        String originalFileName) {
        return new ProductDetailResponse(
            productId, productName, price, description, originalFileName);
    }
}
