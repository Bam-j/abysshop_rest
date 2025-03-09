package com.joo.abysshop.dto.product;

public record ProductListResponse(
    Long productId,
    String productName,
    Long price,
    String originalFileName
) {

    public static ProductListResponse of(
        Long productId,
        String productName,
        Long price,
        String originalFileName) {
        return new ProductListResponse(productId, productName, price, originalFileName);
    }
}
