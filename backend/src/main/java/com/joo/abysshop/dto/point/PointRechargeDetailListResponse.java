package com.joo.abysshop.dto.point;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PointRechargeDetailListResponse {

    private Long rechargeDetailId;
    private Long rechargeId;
    private String nickname;
    private String bank;
    private String accountNumber;
    private Date depositConfirmedTime;
}
