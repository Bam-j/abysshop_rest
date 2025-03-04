package com.joo.abysshop.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddProductImageEntity {

    private Long productId;
    private String originalFileName;
}
