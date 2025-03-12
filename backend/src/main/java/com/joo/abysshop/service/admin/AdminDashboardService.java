package com.joo.abysshop.service.admin;

import com.joo.abysshop.repository.order.OrderRepository;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final OrderRepository orderRepository;
    private final PointRechargeRepository pointRechargeRepository;
    private final PointRechargeDetailRepository pointRechargeDetailRepository;
    /*
     *  Pageable 적용된 주문 목록, 포인트 충전 요청 목록, 충전 요청 상세 목록 가져오는
     *  서비스 메소드들 작성
     */
}
