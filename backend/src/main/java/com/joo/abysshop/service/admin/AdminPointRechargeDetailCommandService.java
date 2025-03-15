package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.service.point.PointRechargeDetailCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeDetailCommandService {

    private final PointRechargeDetailCommandService pointRechargeDetailCommandService;

    public void updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        pointRechargeDetailCommandService.updatePointRechargeDetail(updatePointRechargeDetailRequest);
    }
}
