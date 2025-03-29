package com.joo.abysshop.controller.cart;

import com.joo.abysshop.dto.cart.response.CartAndItemsResponse;
import com.joo.abysshop.service.cart.CartCommandService;
import com.joo.abysshop.service.cart.CartQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartQueryService cartQueryService;
    private final CartCommandService cartCommandService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartAndItemsResponse> getCartByUserId(
        @PathVariable("userId") Long userId) {
        CartAndItemsResponse response = cartQueryService.getCartAndItem(userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable("cartId") Long cartId) {
        cartCommandService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{cartId}/quantity")
    public ResponseEntity<Long> getCartQuantity(@PathVariable("cartId") Long cartId) {
        Long cartQuantity = cartQueryService.getCartTotalQuantity(cartId);
        return ResponseEntity.ok(cartQuantity);
    }
}
