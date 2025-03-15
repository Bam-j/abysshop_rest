package com.joo.abysshop.service.admin;

import com.joo.abysshop.dto.order.OrderListResponse;
import com.joo.abysshop.repository.order.OrderRepository;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final OrderRepository orderRepository;
    private final PointRechargeRepository pointRechargeRepository;
    private final PointRechargeDetailRepository pointRechargeDetailRepository;

    public Page<OrderListResponse> getPagedOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderListResponse::new);
    }
}
