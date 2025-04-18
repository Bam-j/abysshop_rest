package com.joo.abysshop.controller.cart;

import com.joo.abysshop.dto.cart.request.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.request.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.request.UpdateCartItemsQuantityRequest;
import com.joo.abysshop.service.cart.CartItemCommandService;
import com.joo.abysshop.util.security.CustomUserDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemCommandService cartItemCommandService;

    @PostMapping("/add")
    public ResponseEntity<Void> addItemToCart(
        @RequestBody AddItemToCartRequest addItemToCartRequest,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartItemCommandService.addOrUpdateCartItem(customUserDetails.getUserId(), addItemToCartRequest.productId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteItemFromCart(
        @RequestBody DeleteItemFromCartRequest deleteItemFromCartRequest,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartItemCommandService.deleteCartItem(deleteItemFromCartRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/quantity")
    public ResponseEntity<Void> updateCartItemsQuantity(
        @RequestBody List<UpdateCartItemsQuantityRequest> requestList,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartItemCommandService.updateCartItemsQuantity(requestList);
        return ResponseEntity.noContent().build();
    }
}
