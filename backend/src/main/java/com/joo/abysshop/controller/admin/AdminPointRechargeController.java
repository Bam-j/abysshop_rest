package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.UpdatePointRechargeStateRequest;
import com.joo.abysshop.service.admin.AdminPointRechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/point-recharges")
@RequiredArgsConstructor
public class AdminPointRechargeController {

    private final AdminPointRechargeService adminPointRechargeService;

    @PatchMapping("/state")
    public ResponseEntity<Void> updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        adminPointRechargeService.updatePointRechargeState(updatePointRechargeStateRequest);
        return ResponseEntity.noContent().build();
    }
}
