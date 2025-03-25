package com.joo.abysshop.service.order;

import com.joo.abysshop.dto.admin.request.UpdateOrderStateRequest;
import com.joo.abysshop.dto.order.request.CreateOrderRequest;
import com.joo.abysshop.dto.point.request.DeductPointsRequest;
import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.factory.OrderFactory;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.order.OrderRepository;
import com.joo.abysshop.service.user.UserPointCommandService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderQueryService orderQueryService;
    private final UserPointCommandService userPointCommandService;

    @Transactional
    public void updateOrderState(UpdateOrderStateRequest updateOrderStateRequest) {
        Order order = orderQueryService.findById(updateOrderStateRequest.orderId());
        order.updateOrderState(updateOrderStateRequest.newState());
    }

    @Transactional
    public void createOrder(CreateOrderRequest createOrderRequest) {
        Cart cart = cartRepository.findById(createOrderRequest.cartId())
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));

        Order order = OrderFactory.of(cart);
        Long orderTotalPrice = order.getTotalPrice();
        DeductPointsRequest deductPointsRequest = DeductPointsRequest.of(
            createOrderRequest.userId(), orderTotalPrice);

        userPointCommandService.deductPoints(deductPointsRequest);
        orderRepository.save(order);
    }
}
