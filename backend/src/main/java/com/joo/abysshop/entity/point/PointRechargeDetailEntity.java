package com.joo.abysshop.entity.point;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PointRechargeDetailEntity {

    private Long rechargeDetailId;
    private Long rechargeId;
    private Long userId;
    private String bank;
    private String accountNumber;
    private Date depositConfirmedTime;
}
