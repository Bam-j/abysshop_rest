package com.joo.abysshop.factory;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.user.User;

public class CartFactory {

    public static Cart of(User user) {
        return Cart.builder()
            .user(user)
            .totalQuantity(0L)
            .totalPrice(0L)
            .build();
    }
}
