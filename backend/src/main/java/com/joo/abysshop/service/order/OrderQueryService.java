package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.admin.response.AdminOrderListResponse;
import com.joo.abysshop.dto.user.response.UserOrderListResponse;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.order.OrderRepository;
import com.joo.abysshop.repository.user.UserRepository;
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
    private final UserRepository userRepository;

    public Page<AdminOrderListResponse> getPagedAdminOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable).map(AdminOrderListResponse::new);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("주문이 존재하지 않습니다."));
    }

    public Page<UserOrderListResponse> getPagedUserOrderList(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        return orderRepository.findByUser(user, pageable).map(UserOrderListResponse::new);
    }
}
