package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.order.AdminOrderListResponse;
import com.joo.abysshop.service.order.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final OrderQueryService orderQueryService;

    public Page<AdminOrderListResponse> getPagedOrderList(Pageable pageable) {
        return orderQueryService.getPagedAdminOrderList(pageable);
    }
}
