package com.joo.abysshop.entity.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreatePointRechargeEntity {

    private Long userId;
    private String nickname;
    private Long points;
}
