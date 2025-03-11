package com.joo.abysshop.controller.order;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.dto.order.CreateOrderRequest;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.order.OrderService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(CreateOrderRequest createOrderRequest) {
        ResultStatus createOrderResult = orderService.createOrder(createOrderRequest);

        if (createOrderResult.equals(ResultStatus.SUCCESS)) {
            //point가 차감된 유저 정보 토큰 재발급
            return ResponseEntity.ok().build();
        } else if (createOrderResult.equals(ResultStatus.INSUFFICIENT_POINTS)) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "포인트가 부족합니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "결제 요청에 실패하였습니다."));
        }
    }
}