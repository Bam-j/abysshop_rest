package com.joo.abysshop.controller.cart;

import com.joo.abysshop.dto.cart.request.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.request.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.request.UpdateCartItemsQuantityRequest;
import com.joo.abysshop.service.cart.CartItemCommandService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        @RequestBody AddItemToCartRequest addItemToCartRequest) {
        cartItemCommandService.addOrUpdateCartItem(addItemToCartRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteItemFromCart(
        @RequestBody DeleteItemFromCartRequest deleteItemFromCartRequest) {
        cartItemCommandService.deleteCartItem(deleteItemFromCartRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/quantity")
    public ResponseEntity<Void> updateCartItemsQuantity(
        @RequestBody List<UpdateCartItemsQuantityRequest> requestList) {
        cartItemCommandService.updateCartItemsQuantity(requestList);
        return ResponseEntity.noContent().build();
    }
}
