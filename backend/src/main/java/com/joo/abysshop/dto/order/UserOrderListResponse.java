package com.joo.abysshop.dto.order;

import com.joo.abysshop.entity.order.Order;
import java.time.LocalDateTime;

public record UserOrderListResponse(
    Long orderId,
    Long userId,
    LocalDateTime orderedAt,
    Long totalPrice,
    String orderState) {

    public UserOrderListResponse(Order order) {
        this(
            order.getOrderId(),
            order.getUser().getUserId(),
            order.getOrderedAt(),
            order.getTotalPrice(),
            order.getOrderState().name()
        );
    }

    public static UserOrderListResponse of(
        Long orderId,
        Long userId,
        LocalDateTime orderedAt,
        Long totalPrice,
        String orderState) {
        return new UserOrderListResponse(
            orderId,
            userId,
            orderedAt,
            totalPrice,
            orderState);
    }
}
