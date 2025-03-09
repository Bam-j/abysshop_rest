package com.joo.abysshop.dto.point;

import java.util.Date;

public record PointRechargeListResponse(
    Long rechargeId,
    Long userId,
    String nickname,
    Long points,
    Date requestTime,
    String rechargeRequestState
) {

    public static PointRechargeListResponse of(
        Long rechargeId,
        Long userId,
        String nickname,
        Long points,
        Date requestTime,
        String rechargeRequestState) {
        return new PointRechargeListResponse(rechargeId, userId, nickname,
            points, requestTime, rechargeRequestState);
    }
}
