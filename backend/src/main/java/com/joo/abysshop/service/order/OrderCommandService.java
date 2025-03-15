package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.admin.UpdateOrderStateRequest;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final OrderQueryService orderQueryService;

    @Transactional
    public void updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        Order order = orderQueryService.findById(updateOrderStateRequest.orderId());
        order.updateOrderState(updateOrderStateRequest.newState());
    }
}
