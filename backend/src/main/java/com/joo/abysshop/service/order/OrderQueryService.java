package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.admin.response.AdminOrderListResponse;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.repository.order.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public Page<AdminOrderListResponse> getPagedAdminOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable).map(AdminOrderListResponse::new);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("주문이 존재하지 않습니다."));
    }
}
