package com.joo.abysshop.dto.order;

import java.util.Date;

public record OrderListResponse(
    Long orderId,
    Long userId,
    String nickname,
    Date orderDate,
    Long totalPoints,
    String orderState) {

    public static OrderListResponse of(
        Long orderId,
        Long userId,
        String nickname,
        Date orderDate,
        Long totalPoints,
        String orderState) {
        return new OrderListResponse(orderId, userId, nickname, orderDate, totalPoints, orderState);
    }
}
