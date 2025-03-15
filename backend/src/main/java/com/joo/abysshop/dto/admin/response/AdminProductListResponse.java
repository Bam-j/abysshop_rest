package com.joo.abysshop.dto.admin.response;

import com.joo.abysshop.entity.product.Product;

public record AdminProductListResponse(
    Long productId,
    String productName,
    Long price
) {

    public AdminProductListResponse(Product product) {
        this(
            product.getProductId(),
            product.getProductName(),
            product.getPrice()
        );
    }

    public static AdminProductListResponse of(
        Long productId,
        String productName,
        Long price
    ) {
        return new AdminProductListResponse(productId, productName, price);
    }
}
