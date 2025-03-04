package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.entity.order.OrderEntity;

public interface ToOrderDTOMapper {

    OrderListResponse toOrderListResponse(OrderEntity orderEntity);

    OrderListResponse toOrderListResponse(OrderEntity orderEntity, String nickname);
}
