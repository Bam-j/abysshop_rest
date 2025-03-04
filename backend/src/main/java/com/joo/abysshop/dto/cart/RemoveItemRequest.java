package com.joo.abysshop.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RemoveItemRequest {

    private Long cartId;
    private Long productId;
    private Long userId;
}
