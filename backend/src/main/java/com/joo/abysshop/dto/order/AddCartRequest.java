package com.joo.abysshop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddCartRequest {

    private Integer userId;
    private Integer productId;
    private Integer cartId;
}
