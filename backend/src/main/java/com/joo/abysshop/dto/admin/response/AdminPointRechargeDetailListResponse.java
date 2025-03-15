package com.joo.abysshop.dto.admin.response;

import com.joo.abysshop.entity.point.PointRechargeDetail;
import java.time.LocalDateTime;

public record AdminPointRechargeDetailListResponse(
    Long rechargeDetailId,
    Long pointRechargeId,
    String bank,
    String accountNumber,
    Long depositAmount,
    LocalDateTime depositConfirmedAt
) {

    public AdminPointRechargeDetailListResponse(PointRechargeDetail pointRechargeDetail) {
        this(
            pointRechargeDetail.getPointRechargeDetailId(),
            pointRechargeDetail.getPointRecharge().getPointRechargeId(),
            pointRechargeDetail.getBank(),
            pointRechargeDetail.getAccountNumber(),
            pointRechargeDetail.getDepositAmount(),
            pointRechargeDetail.getDepositConfirmedAt()
        );
    }

    public static AdminPointRechargeDetailListResponse of(
        Long rechargeDetailId,
        Long pointRechargeId,
        String bank,
        String accountNumber,
        Long depositAmount,
        LocalDateTime depositConfirmedAt) {
        return new AdminPointRechargeDetailListResponse(
            rechargeDetailId,
            pointRechargeId,
            bank,
            accountNumber,
            depositAmount,
            depositConfirmedAt
        );
    }
}
