package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.UpdateOrderStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @PatchMapping("/state")
    public ResponseEntity<String> updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        adminOrderService.updateOrderState(updateOrderStateRequest);
        return ResponseEntity.noContent().build();
    }
}
