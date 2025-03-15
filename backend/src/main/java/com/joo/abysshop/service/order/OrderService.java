package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.order.CreateOrderRequest;
import com.joo.abysshop.repository.order.OrderRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    //하나의 서비스는 CQRS 패턴 적용해서 분리하기

    public ResultStatus createOrder(CreateOrderRequest createOrderRequest) {
        /*
         *  1. cart에서 결제 포인트 가져와서 유저 pointBalance에서 차감시키기 > 잔액 부족: INSUFFICIENT_POINTS
         *  2. cart 정보 토대로 Order 엔티티 만들어서 orders_table에 insert
         *  3. point가 차감되어 갱신된 유저 JWT 인증 토큰 재발급
         *  4. 불필요한 구버전 코드 제거하기
         */
        Long cartId = createOrderRequest.getCartId();
        Long userId = createOrderRequest.getUserId();
        Long totalPoints = cartService.getTotalPoints(cartId);
        Long userPoints = userMapper.getUserPoints(userId);

        if (userPoints < totalPoints) {
            return ResultStatus.INSUFFICIENT_POINTS;
        }

        CreateOrderEntity createOrderEntity = CreateOrderEntity.builder()
            .userId(userId)
            .totalPoints(totalPoints)
            .build();
        orderMapper.insertOrder(createOrderEntity);

        Long remainPoints = userPoints - totalPoints;
        Map<String, Object> remainPointsMap = new HashMap<>();
        remainPointsMap.put("userId", userId);
        remainPointsMap.put("remainPoints", remainPoints);
        userMapper.consumePoint(remainPointsMap);

        return ResultStatus.SUCCESS;
    }

    public int getTotalOrderCount() {
        return orderMapper.countAllOrders();
    }
}
