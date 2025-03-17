package com.joo.abysshop.dto.product.response;

import com.joo.abysshop.entity.product.Product;

public record ProductListResponse(
    Long productId,
    String productName,
    Long price,
    String fileName
) {

    public ProductListResponse(Product product) {
        this(
            product.getProductId(),
            product.getProductName(),
            product.getPrice(),
            product.getProductImage().getFileName()
        );
    }

    public static ProductListResponse of(
        Long productId,
        String productName,
        Long price,
        String fileName) {
        return new ProductListResponse(productId, productName, price, fileName);
    }
}
