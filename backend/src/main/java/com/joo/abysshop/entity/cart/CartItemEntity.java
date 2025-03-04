package com.joo.abysshop.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartItemEntity {

    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private String productName;
    private Long price; // price = product_id.price * quantity한 담긴 수량의 총 금액을 의미합니다.
    private Long quantity;
}
