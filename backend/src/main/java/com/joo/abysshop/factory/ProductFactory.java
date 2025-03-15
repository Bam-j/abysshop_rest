package com.joo.abysshop.factory;

import com.joo.abysshop.dto.admin.CreateProductRequest;
import com.joo.abysshop.entity.product.Product;

public class ProductFactory {

    public static Product of(CreateProductRequest createProductRequest) {
        return Product.builder()
            .productName(createProductRequest.productName())
            .price(createProductRequest.price())
            .description(createProductRequest.description())
            .build();
    }
}
