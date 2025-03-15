package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.UpdateOrderStateRequest;
import com.joo.abysshop.service.admin.AdminOrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderCommandService adminOrderCommandService;

    @PatchMapping("/state")
    public ResponseEntity<Void> updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        adminOrderCommandService.updateOrderState(updateOrderStateRequest);
        return ResponseEntity.noContent().build();
    }
}
