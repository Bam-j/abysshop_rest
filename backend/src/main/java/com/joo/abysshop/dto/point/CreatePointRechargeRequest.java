package com.joo.abysshop.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreatePointRechargeRequest {

    private Long userId;
    private String nickname;
    private Long points;
}
