package com.joo.abysshop.controller.order;

import com.joo.abysshop.dto.order.request.CreateOrderRequest;
import com.joo.abysshop.service.order.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderCommandService orderCommandService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderCommandService.createOrder(createOrderRequest);
        return ResponseEntity.noContent().build();
    }
}