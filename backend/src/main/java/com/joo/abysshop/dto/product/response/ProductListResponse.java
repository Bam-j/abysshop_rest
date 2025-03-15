package com.joo.abysshop.dto.product.response;

public record ProductListResponse(
    Long productId,
    String productName,
    Long price,
    String fileName
) {

    public static ProductListResponse of(
        Long productId,
        String productName,
        Long price,
        String fileName) {
        return new ProductListResponse(productId, productName, price, fileName);
    }
}
