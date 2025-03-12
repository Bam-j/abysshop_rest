package com.joo.abysshop.controller.cart;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.UpdateCartItemsQuantityRequest;
import com.joo.abysshop.service.cart.CartItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<Void> addItemToCart(AddItemToCartRequest addItemToCartRequest) {
        //TODO: 사용자 검사 -> 관리자/비로그인 상태는 요청 수행 취소
        cartItemService.addOrUpdateCartItem(addItemToCartRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteItemFromCart(
        DeleteItemFromCartRequest deleteItemFromCartRequest) {
        cartItemService.deleteCartItem(deleteItemFromCartRequest);
        return ResponseEntity.noContent().build();
    }

    /*
     *   TODO: 수량증가 방식 변경!
     *       기존에 클릭마다 요청을 보냈던 비효율 방식에서
     *       프론트에서 수량 조절이 감지되면 적당한 시간(1~5초) 후에 데이터를 종합해서
     *       서버로 요청을 보내는 방식으로 변경해서 구현
     */
    @PatchMapping("/quantity")
    public ResponseEntity<Void> updateCartItemsQuantity(
        List<UpdateCartItemsQuantityRequest> requestList) {
        cartItemService.updateCartItemsQuantity(requestList);
        return ResponseEntity.noContent().build();
    }
}
