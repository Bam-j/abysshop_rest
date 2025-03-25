package com.joo.abysshop.dto.admin.response;

import com.joo.abysshop.entity.point.PointRechargeDetail;
import java.time.LocalDateTime;

public record AdminPointRechargeDetailListResponse(
    Long pointRechargeDetailId,
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
        Long pointRechargeDetailId,
        Long pointRechargeId,
        String bank,
        String accountNumber,
        Long depositAmount,
        LocalDateTime depositConfirmedAt) {
        return new AdminPointRechargeDetailListResponse(
            pointRechargeDetailId,
            pointRechargeId,
            bank,
            accountNumber,
            depositAmount,
            depositConfirmedAt
        );
    }
}
