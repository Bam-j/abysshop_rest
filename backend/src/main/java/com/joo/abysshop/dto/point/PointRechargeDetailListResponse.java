package com.joo.abysshop.dto.point;

import java.util.Date;

public record PointRechargeDetailListResponse(
    Long rechargeDetailId,
    Long rechargeId,
    String nickname,
    String bank,
    String accountNumber,
    Long depositAmount,
    Date depositConfirmedTime
) {

    public static PointRechargeDetailListResponse of(
        Long rechargeDetailId,
        Long rechargeId,
        String nickname,
        String bank,
        String accountNumber,
        Long depositAmount,
        Date depositConfirmedTime) {
        return new PointRechargeDetailListResponse(rechargeDetailId, rechargeId, nickname,
            bank, accountNumber, depositAmount, depositConfirmedTime);
    }
}
