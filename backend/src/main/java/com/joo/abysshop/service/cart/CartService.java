package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.response.CartResponse;
import com.joo.abysshop.repository.cart.CartItemRepository;
import com.joo.abysshop.repository.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartResponse getCartByUserId(Long userId) {
        /*
         *  1. userId(fk)로 특정 회원의 카트 불러오기
         *  2. 이때 카트 총합 정보인 Cart와 거기에 담긴 아이템 정보인 CartItem을 모두 가져온다.
         */
    }

    public void clearCart(Long cartId) {
        /*
         * 1. cartId를 가진 Cart와 CartItem(FK) 레코드를 모두 DELETE
         * 2. 이때 Cart의 totalPrice와 totalQuantity는 0L로 만들어야 함
         * 3. 구버전 코드(아래 코드들)은 삭제
         */
        if (cartId == null) {
            return;
        }

        cartMapper.deleteCartItems(cartId);
        cartMapper.resetCartValues(cartId);
    }
}
