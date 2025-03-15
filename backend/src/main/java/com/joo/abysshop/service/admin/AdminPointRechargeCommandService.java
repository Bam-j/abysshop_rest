package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.UpdatePointRechargeStateRequest;
import com.joo.abysshop.service.point.PointRechargeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeCommandService {

    private final PointRechargeCommandService pointRechargeCommandService;

    public void updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        pointRechargeCommandService.updatePointRechargeState(updatePointRechargeStateRequest);
    }
}
