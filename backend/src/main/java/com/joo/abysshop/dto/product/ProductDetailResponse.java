package com.joo.abysshop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductDetailResponse {

    private Long productId;
    private String productName;
    private Long price;
    private String description;
    private String originalFileName;
}
