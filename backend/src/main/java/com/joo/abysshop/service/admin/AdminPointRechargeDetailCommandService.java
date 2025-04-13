package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.point.request.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.service.point.PointRechargeDetailCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeDetailCommandService {

    private final PointRechargeDetailCommandService pointRechargeDetailCommandService;

    @Transactional
    public void updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        pointRechargeDetailCommandService.updatePointRechargeDetail(updatePointRechargeDetailRequest);
    }
}
