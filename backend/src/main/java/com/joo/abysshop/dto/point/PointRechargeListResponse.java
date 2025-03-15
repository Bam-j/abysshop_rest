package com.joo.abysshop.dto.point;

import java.time.LocalDateTime;

public record PointRechargeListResponse(
    Long pointRechargeId,
    Long userId,
    Long requestedPoints,
    LocalDateTime requestedAt,
    String rechargeState
) {

    public static PointRechargeListResponse of(
        Long pointRechargeId,
        Long userId,
        Long requestedPoints,
        LocalDateTime requestedAt,
        String rechargeState) {
        return new PointRechargeListResponse(
            pointRechargeId,
            userId,
            requestedPoints,
            requestedAt,
            rechargeState);
    }
}
