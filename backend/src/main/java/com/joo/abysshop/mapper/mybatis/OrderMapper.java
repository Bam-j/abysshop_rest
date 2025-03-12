package com.joo.abysshop.mapper.mybatis;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    void insertOrder(CreateOrderEntity createOrderEntity);

    int countAllOrders();

    int countUserOrders(Long userId);

    List<OrderEntity> findPagedUserOrders(@Param("userId") Long userId,
        @Param("pageSize") int pageSize, @Param("offset") int offset);
}
