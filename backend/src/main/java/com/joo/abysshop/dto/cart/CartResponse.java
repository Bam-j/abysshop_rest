package com.joo.abysshop.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartResponse {

    private Long cartId;
    private Long userId;
    private Long quantity;
    private Long totalPoints;
}
