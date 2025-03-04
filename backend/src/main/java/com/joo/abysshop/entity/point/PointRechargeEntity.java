package com.joo.abysshop.entity.point;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PointRechargeEntity {

    private Long rechargeId;
    private Long userId;
    private String nickname;
    private Long points;
    private Date requestTime;
    private String rechargeRequestState;
}
