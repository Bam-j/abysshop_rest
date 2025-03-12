package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.admin.UpdatePointRechargeStateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPointRechargeService {

    public void updatePointRechargeState(
        UpdatePointRechargeStateRequest updatePointRechargeStateRequest) {
        /*
         *  1. pointRechargeId 탐색 후 update requestedState
         */
    }
}
