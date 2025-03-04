package com.joo.abysshop.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductEntity {

    private Long productId;
    private String productName;
    private Long price;
    private String description;
}
