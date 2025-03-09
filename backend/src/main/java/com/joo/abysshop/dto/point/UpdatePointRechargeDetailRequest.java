package com.joo.abysshop.dto.point;

public record UpdatePointRechargeDetailRequest(
    Long rechargeDetailId,
    String bank,
    String accountNumber,
    Long depositAmount) {


    public static UpdatePointRechargeDetailRequest of(
        Long rechargeDetailId,
        String bank,
        String accountNumber,
        Long depositAmount) {
        return new UpdatePointRechargeDetailRequest(rechargeDetailId,
            bank, accountNumber, depositAmount);
    }
}
