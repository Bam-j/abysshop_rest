package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.UpdateOrderStateRequest;
import com.joo.abysshop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrderRepository orderRepository;

    public void updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        /*
         *  1. orderId로 탐색해서 update orderState
         */
    }
}
