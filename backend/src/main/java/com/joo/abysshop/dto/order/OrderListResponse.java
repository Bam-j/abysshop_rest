package com.joo.abysshop.dto.order;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderListResponse {

    private Long orderId;
    private Long userId;
    private String nickname;
    private Date orderDate;
    private Long totalPoints;
    private String orderState;
}
