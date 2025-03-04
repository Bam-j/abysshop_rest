package com.joo.abysshop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
class OrderProductEntity {

    private Long userId;
    private Long productId;
    private Long cartId;
    private Long orderId;
}
