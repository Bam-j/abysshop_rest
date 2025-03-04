package com.joo.abysshop.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdatePointRechargeDetailRequest {

    private Long rechargeDetailId;
    private String bank;
    private String accountNumber;
}
