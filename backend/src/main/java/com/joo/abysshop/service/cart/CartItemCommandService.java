package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.request.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.request.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.cart.request.UpdateCartItemsQuantityRequest;
import com.joo.abysshop.dto.cart.response.CartItemDetailResponse;
import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.cart.CartItem;
import com.joo.abysshop.entity.product.Product;
import com.joo.abysshop.factory.CartItemFactory;
import com.joo.abysshop.repository.cart.CartItemRepository;
import com.joo.abysshop.repository.cart.CartRepository;
import com.joo.abysshop.repository.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemCommandService {

    private final CartItemRepository cartItemRepository;
    private final CartItemQueryService cartItemQueryService;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void clearCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));
        cartItemRepository.deleteAllByCart(cart);
    }

    @Transactional
    public void addOrUpdateCartItem(AddItemToCartRequest addItemToCartRequest) {
        Long cartId = addItemToCartRequest.cartId();
        Long productId = addItemToCartRequest.productId();

        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("상품이 존재하지 않습니다."));

        cartItemRepository.findByCartAndProduct(cart, product)
            .ifPresentOrElse(
                cartItem -> {
                    cartItem.increaseQuantity(1L);
                    cartItemRepository.save(cartItem);
                },
                () -> cartItemRepository.save(CartItemFactory.of(cart, product))
            );
    }

    @Transactional
    public void deleteCartItem(DeleteItemFromCartRequest deleteItemFromCartRequest) {
        CartItemDetailResponse cartItemDetail = cartItemQueryService.getCartItemDetail(
            deleteItemFromCartRequest.productId());
        Cart cart = cartRepository.findById(deleteItemFromCartRequest.cartId())
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));

        cart.deleteItemFromCart(cartItemDetail.quantity(), cartItemDetail.getTotalPrice());
    }

    public void updateCartItemsQuantity(List<UpdateCartItemsQuantityRequest> requestList) {
        Long cartId = requestList.get(0).cartId();
        Long totalPrice = 0L;

        for (UpdateCartItemsQuantityRequest request : requestList) {
            Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new EntityNotFoundException("상품이 존재하지 않습니다."));
            CartItem cartItem = cartItemRepository.findByProduct(product)
                .orElseThrow(() -> new EntityNotFoundException("장바구니에 해당 상품이 존재하지 않습니다."));

            cartItem.updateQuantity(request.quantity());
            totalPrice += cartItemRepository.findTotalPriceByCartIdAndProductId(request.cartId(),
                request.productId());
        }

        Long totalQuantity = cartItemRepository.findTotalQuantityByCartId(cartId);

        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new EntityNotFoundException("장바구니가 존재하지 않습니다."));
        cart.updateCart(totalQuantity, totalPrice);
    }
}
