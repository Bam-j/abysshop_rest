package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.order.OrderListResponse;

public interface ToOrderDTOMapper {

    OrderListResponse toOrderListResponse(OrderEntity orderEntity);

    OrderListResponse toOrderListResponse(OrderEntity orderEntity, String nickname);
}
