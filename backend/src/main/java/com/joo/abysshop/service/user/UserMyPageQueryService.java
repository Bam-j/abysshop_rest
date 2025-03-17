package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.user.response.UserOrderListResponse;
import com.joo.abysshop.dto.user.response.UserPointRechargeListResponse;
import com.joo.abysshop.service.order.OrderQueryService;
import com.joo.abysshop.service.point.PointRechargeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMyPageQueryService {

    private final OrderQueryService orderQueryService;
    private final PointRechargeQueryService pointRechargeQueryService;

    public Page<UserOrderListResponse> getPagedUserOrders(Long userId, Pageable pageable) {
        return orderQueryService.getPagedUserOrderList(userId, pageable);
    }

    public Page<UserPointRechargeListResponse> getPagedUserPointRecharges(Long userId,
        Pageable pageable) {
        return pointRechargeQueryService.getPagedUserPointRechargeList(userId, pageable);
    }
}
