package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.order.AdminOrderListResponse;
import com.joo.abysshop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public Page<AdminOrderListResponse> getPagedAdminOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable).map(AdminOrderListResponse::new);
    }
}
