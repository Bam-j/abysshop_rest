package com.joo.abysshop.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddProductEntity {

    private Long productId;
    private String productName;
    private Long price;
    private String description;
}
