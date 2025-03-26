package com.joo.abysshop.dto.product.response;

import com.joo.abysshop.entity.product.Product;

public record ProductDetailResponse(
    Long productId,
    String productName,
    Long price,
    String description,
    String fileName
) {

    public ProductDetailResponse(Product product) {
        this(
            product.getProductId(),
            product.getProductName(),
            product.getPrice(),
            product.getDescription(),
            product.getProductImage() != null ? product.getProductImage().getFileName() : null
        );
    }

    public static ProductDetailResponse of(
        Long productId,
        String productName,
        Long price,
        String description,
        String fileName) {
        return new ProductDetailResponse(
            productId, productName, price, description, fileName);
    }
}
