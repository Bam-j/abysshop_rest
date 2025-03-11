package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/point-recharges/details")
@RequiredArgsConstructor
public class AdminPointRechargeDetailController {

    private final AdminPointRechargeDetailService adminPointRechargeDetailService;

    @PatchMapping("/update")
    public ResponseEntity<Void> updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        adminPointRechargeDetailService.updatePointRechargeDetail(updatePointRechargeDetailRequest);
        return ResponseEntity.noContent().build();
    }
}
