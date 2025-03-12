package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.point.CreatePointRechargeRequest;
import com.joo.abysshop.service.point.PointRechargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPointService {

    private final PointRechargeService pointRechargeService;

    public void createPointRecharge(CreatePointRechargeRequest createPointRechargeRequest) {
        /*
         *  1. 입력 정보 토대로 point_recharges_table에 insert
         */
    }
}
