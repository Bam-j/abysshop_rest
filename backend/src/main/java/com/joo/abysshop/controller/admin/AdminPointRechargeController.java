package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.admin.UpdatePointRechargeStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/point-recharges")
@RequiredArgsConstructor
public class AdminPointRechargeController {

    private final AdminPointRecahrgeService adminPointRecahrgeService;

    @PatchMapping("/state")
    public ResponseEntity<Object> updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        adminPointRecahrgeService.updatePointRechargeState(updatePointRechargeStateRequest);
        return ResponseEntity.noContent().build();
    }
}
