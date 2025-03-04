package com.joo.abysshop.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
//order_products op LEFT OUTER JOIN products_table
public class ProductInOrderEntity {

    private Long userId;
    private Long productId;
    private Long cartId;
    private Long orderId;
    private String productName;
    private Long price;
    private String description;
}
