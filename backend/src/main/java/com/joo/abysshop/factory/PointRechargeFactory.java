package com.joo.abysshop.factory;

import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.util.enums.RechargeState;
import java.time.LocalDateTime;

public class PointRechargeFactory {

    public static PointRecharge of(User user, Long requestedPoints) {
        return PointRecharge.builder()
            .user(user)
            .requestedPoints(requestedPoints)
            .requestedAt(LocalDateTime.now())
            .rechargeState(RechargeState.PENDING_PAYMENT)
            .build();
    }
}
