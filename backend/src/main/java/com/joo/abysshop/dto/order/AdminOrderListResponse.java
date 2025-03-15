package com.joo.abysshop.dto.order;

import com.joo.abysshop.entity.order.Order;
import java.time.LocalDateTime;

public record AdminOrderListResponse(
    Long orderId,
    Long userId,
    String nickname,
    LocalDateTime orderedAt,
    Long totalPrice,
    String orderState) {

    public AdminOrderListResponse(Order order) {
        this(
            order.getOrderId(),
            order.getUser().getUserId(),
            order.getUser().getNickname(),
            order.getOrderedAt(),
            order.getTotalPrice(),
            order.getOrderState().name()
        );
    }

    public static AdminOrderListResponse of(
        Long orderId,
        Long userId,
        String nickname,
        LocalDateTime orderedAt,
        Long totalPrice,
        String orderState) {
        return new AdminOrderListResponse(
            orderId,
            userId,
            nickname,
            orderedAt,
            totalPrice,
            orderState
        );
    }
}
