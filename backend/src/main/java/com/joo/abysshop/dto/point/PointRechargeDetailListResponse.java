package com.joo.abysshop.dto.point;

import java.time.LocalDateTime;

public record PointRechargeDetailListResponse(
    Long rechargeDetailId,
    Long pointRechargeId,
    String bank,
    String accountNumber,
    Long depositAmount,
    LocalDateTime depositConfirmedAt
) {

    public static PointRechargeDetailListResponse of(
        Long rechargeDetailId,
        Long pointRechargeId,
        String bank,
        String accountNumber,
        Long depositAmount,
        LocalDateTime depositConfirmedAt) {
        return new PointRechargeDetailListResponse(
            rechargeDetailId,
            pointRechargeId,
            bank,
            accountNumber,
            depositAmount,
            depositConfirmedAt);
    }
}
