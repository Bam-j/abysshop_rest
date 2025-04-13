package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.request.UpdateOrderStateRequest;
import com.joo.abysshop.service.order.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminOrderCommandService {

    private final OrderCommandService orderCommandService;

    @Transactional
    public void updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        orderCommandService.updateOrderState(updateOrderStateRequest);
    }
}
