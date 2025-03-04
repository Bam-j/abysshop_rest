package com.joo.abysshop.entity.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdatePointRechargeDetailEntity {

    private Long rechargeDetailId;
    private String bank;
    private String accountNumber;
}
