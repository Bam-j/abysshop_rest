package com.joo.abysshop.dto.admin;

public record UpdatePointRechargeStateRequest(Long rechargeId, String newState) {

    public static UpdateOrderStateRequest of(Long rechargeId, String newState) {
        return new UpdateOrderStateRequest(rechargeId, newState);
    }
}
