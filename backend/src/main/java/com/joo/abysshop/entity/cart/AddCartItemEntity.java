package com.joo.abysshop.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddCartItemEntity {

    private Long cartId;
    private Long productId;
    private String productName;
    private Long price;
}
