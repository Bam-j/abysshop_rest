package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.request.UpdatePointRechargeStateRequest;
import com.joo.abysshop.service.point.PointRechargeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeCommandService {

    private final PointRechargeCommandService pointRechargeCommandService;

    @Transactional
    public void updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        pointRechargeCommandService.updatePointRechargeState(updatePointRechargeStateRequest);
    }
}
