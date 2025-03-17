package com.joo.abysshop.factory;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.util.enums.OrderState;
import java.time.LocalDateTime;

public class OrderFactory {

    public static Order of(Cart cart) {
        return Order.builder()
            .user(cart.getUser())
            .orderedAt(LocalDateTime.now())
            .totalPrice(cart.getTotalPrice())
            .orderState(OrderState.SHIPPING)
            .build();
    }
}
