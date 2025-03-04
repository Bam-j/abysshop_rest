package com.joo.abysshop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderEntity {

    private Long userId;
    private Long totalPoints;
}
