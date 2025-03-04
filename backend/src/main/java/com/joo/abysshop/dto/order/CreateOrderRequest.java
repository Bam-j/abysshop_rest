package com.joo.abysshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderRequest {

    private Long cartId;
    private Long userId;
}
