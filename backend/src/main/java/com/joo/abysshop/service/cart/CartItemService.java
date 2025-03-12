package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.UpdateCartItemsQuantityRequest;
import com.joo.abysshop.repository.cart.CartItemRepository;
import com.joo.abysshop.repository.cart.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public void addOrUpdateCartItem(AddItemToCartRequest addItemToCartRequest) {
        /*
         *  1. 추가하려는 productId를 CartItem에서 탐색.
         *  2. 이미 존재하는 상품인 경우 quantity + 1, price +
         *  3. 존재하지 않는 경우 새로 레코드 insert
         *  4. CartItem 추가 작업 후 Cart의 total~ 증가시키기
         */
    }

    public void deleteCartItem(DeleteItemFromCartRequest deleteItemFromCartRequest) {
        /*
         *  1. CartItem에서 해당 productId 찾아서 레코드 DELETE
         *  2. Cart의 total~ 감소시키기
         */
    }

    public void updateCartItemsQuantity(List<UpdateCartItemsQuantityRequest> requestList) {
        /*
         *  1. list에 담긴 수량이 변경된 모든 product 찾아서 cartItem의 quantity/price 증감
         *  2. Cart의 total~ 증감
         */
    }
}
