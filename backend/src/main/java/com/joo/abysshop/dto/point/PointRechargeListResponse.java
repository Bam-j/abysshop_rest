package com.joo.abysshop.dto.point;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PointRechargeListResponse {

    private Long rechargeId;
    private Long userId;
    private String nickname;
    private Long points;
    private Date requestTime;
    private String rechargeRequestState;
}
