package com.joo.abysshop.dto.point.request;

public record UpdatePointRechargeDetailRequest(
    Long pointRechargeDetailId,
    String bank,
    String accountNumber,
    Long depositAmount) {


    public static UpdatePointRechargeDetailRequest of(
        Long pointRechargeDetailId,
        String bank,
        String accountNumber,
        Long depositAmount) {
        return new UpdatePointRechargeDetailRequest(
            pointRechargeDetailId,
            bank,
            accountNumber,
            depositAmount);
    }
}
