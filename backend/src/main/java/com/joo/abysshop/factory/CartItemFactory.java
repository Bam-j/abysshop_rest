package com.joo.abysshop.factory;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.cart.CartItem;
import com.joo.abysshop.entity.product.Product;

public class CartItemFactory {

    public static CartItem of(Cart cart, Product product) {
        return CartItem.builder()
            .cart(cart)
            .product(product)
            .quantity(1L)
            .build();
    }
}
