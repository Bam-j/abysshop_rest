package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.entity.order.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class ToOrderDTOMapperImpl implements ToOrderDTOMapper {

    @Override
    public OrderListResponse toOrderListResponse(OrderEntity orderEntity) {
        return OrderListResponse.builder()
            .orderId(orderEntity.getOrderId())
            .userId(orderEntity.getUserId())
            .orderDate(orderEntity.getOrderDate())
            .totalPoints(orderEntity.getTotalPoints())
            .orderState(orderEntity.getOrderState())
            .build();
    }

    @Override
    public OrderListResponse toOrderListResponse(OrderEntity orderEntity, String nickname) {
        return OrderListResponse.builder()
            .orderId(orderEntity.getOrderId())
            .userId(orderEntity.getUserId())
            .nickname(nickname)
            .orderDate(orderEntity.getOrderDate())
            .totalPoints(orderEntity.getTotalPoints())
            .orderState(orderEntity.getOrderState())
            .build();
    }
}
