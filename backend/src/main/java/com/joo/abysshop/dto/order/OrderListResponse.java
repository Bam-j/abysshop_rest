package com.joo.abysshop.dto.order;

import com.joo.abysshop.entity.order.Order;
import java.time.LocalDateTime;

public record OrderListResponse(
    Long orderId,
    Long userId,
    String nickname,
    LocalDateTime orderedAt,
    Long totalPrice,
    String orderState) {

    public OrderListResponse(Order order) {
        this(
            order.getOrderId(),
            order.getUser().getUserId(),
            order.getUser().getNickname(),
            order.getOrderedAt(),
            order.getTotalPrice(),
            order.getOrderState().name()
        );
    }

    public static OrderListResponse of(
        Long orderId,
        Long userId,
        String nickname,
        LocalDateTime orderedAt,
        Long totalPrice,
        String orderState) {
        return new OrderListResponse(orderId, userId, nickname, orderedAt, totalPrice, orderState);
    }
}
