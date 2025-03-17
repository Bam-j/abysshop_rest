package com.joo.abysshop.dto.user.response;

import com.joo.abysshop.entity.point.PointRecharge;
import java.time.LocalDateTime;

public record UserPointRechargeListResponse(
    Long pointRechargeId,
    Long userId,
    Long requestedPoints,
    LocalDateTime requestedAt,
    String rechargeState
) {

    public UserPointRechargeListResponse(PointRecharge pointRecharge) {
        this(
            pointRecharge.getPointRechargeId(),
            pointRecharge.getUser().getUserId(),
            pointRecharge.getRequestedPoints(),
            pointRecharge.getRequestedAt(),
            pointRecharge.getRechargeState().name()
        );
    }
}
