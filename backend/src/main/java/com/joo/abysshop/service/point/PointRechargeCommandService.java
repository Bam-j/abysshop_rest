package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.admin.UpdatePointRechargeStateRequest;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointRechargeCommandService {

    private final PointRechargeRepository pointRechargeRepository;
    private final PointRechargeQueryService pointRechargeQueryService;

    @Transactional
    public void updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        PointRecharge pointRecharge = pointRechargeQueryService.findById(
            updatePointRechargeStateRequest.rechargeId());
        pointRecharge.updatePointRechargeState(updatePointRechargeStateRequest.newState());
    }
}
