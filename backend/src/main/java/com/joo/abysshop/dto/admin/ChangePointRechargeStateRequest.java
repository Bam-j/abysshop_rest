package com.joo.abysshop.dto.admin;

public record ChangePointRechargeStateRequest(Long rechargeId, String newState) {

    public static ChangeOrderStateRequest of(Long rechargeId, String newState) {
        return new ChangeOrderStateRequest(rechargeId, newState);
    }
}
