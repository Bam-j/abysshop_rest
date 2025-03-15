package com.joo.abysshop.controller.admin;

import com.joo.abysshop.dto.point.request.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.service.admin.AdminPointRechargeDetailCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/point-recharges/details")
@RequiredArgsConstructor
public class AdminPointRechargeDetailController {

    private final AdminPointRechargeDetailCommandService adminPointRechargeDetailCommandService;

    @PatchMapping("/update")
    public ResponseEntity<Void> updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        adminPointRechargeDetailCommandService.updatePointRechargeDetail(updatePointRechargeDetailRequest);
        return ResponseEntity.noContent().build();
    }
}
