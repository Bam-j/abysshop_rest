package com.joo.abysshop.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartEntity {

    private Long cartId;
    private Long userId;
    private Long quantity;
    private Long totalPoints;
}
