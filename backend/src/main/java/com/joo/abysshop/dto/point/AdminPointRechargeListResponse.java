package com.joo.abysshop.dto.point;

import com.joo.abysshop.entity.point.PointRecharge;
import java.time.LocalDateTime;

public record AdminPointRechargeListResponse(
    Long pointRechargeId,
    Long userId,
    String nickname,
    Long requestedPoints,
    LocalDateTime requestedAt,
    String rechargeState
) {

    public AdminPointRechargeListResponse(PointRecharge pointRecharge) {
        this(
            pointRecharge.getPointRechargeId(),
            pointRecharge.getUser().getUserId(),
            pointRecharge.getUser().getNickname(),
            pointRecharge.getRequestedPoints(),
            pointRecharge.getRequestedAt(),
            pointRecharge.getRechargeState().name()
        );
    }

    public static AdminPointRechargeListResponse of(
        Long pointRechargeId,
        Long userId,
        String nickname,
        Long requestedPoints,
        LocalDateTime requestedAt,
        String rechargeState) {
        return new AdminPointRechargeListResponse(
            pointRechargeId,
            userId,
            nickname,
            requestedPoints,
            requestedAt,
            rechargeState);
    }
}
