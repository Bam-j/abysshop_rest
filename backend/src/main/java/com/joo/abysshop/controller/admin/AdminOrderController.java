package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.UpdateOrderStateRequest;
import com.joo.abysshop.service.admin.AdminOrderService;
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
    public ResponseEntity<Void> updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        adminOrderService.updateOrderState(updateOrderStateRequest);
        return ResponseEntity.noContent().build();
    }
}
