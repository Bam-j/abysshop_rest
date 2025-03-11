package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.AddItemToCartRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.DeleteItemFromCartRequest;
import com.joo.abysshop.dto.product.ProductInfoRequest;
import com.joo.abysshop.entity.cart.AddCartItemEntity;
import com.joo.abysshop.entity.cart.CartEntity;
import com.joo.abysshop.entity.cart.CartItemEntity;
import com.joo.abysshop.mapper.dto.ToCartDTOMapper;
import com.joo.abysshop.mapper.entity.ToCartEntityMapper;
import com.joo.abysshop.mapper.mybatis.CartMapper;
import com.joo.abysshop.service.product.ProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartMapper cartMapper;
    private final ToCartDTOMapper toCartDTOMapper;
    private final ToCartEntityMapper toCartEntityMapper;

    public CartResponse getCart(Long userId) {
        CartEntity cartEntity = cartMapper.getCart(userId);
        return toCartDTOMapper.toCartResponse(cartEntity);
    }

    public List<CartItemResponse> getUserCartItems(Long cartId) {
        List<CartItemEntity> cartItemEntityList = cartMapper.getCartItems(cartId);
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();

        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            cartItemResponseList.add(toCartDTOMapper.toCartItemResponse(cartItemEntity));
        }

        return cartItemResponseList;
    }

    public void addItem(AddItemToCartRequest addItemToCartRequest) {
        Long productId = addItemToCartRequest.getProductId();
        ProductInfoRequest productInfoRequest = productService.getProductInfo(productId);
        AddCartItemEntity addCartItemEntity = toCartEntityMapper.toAddCartItemEntity(
            addItemToCartRequest,
            productInfoRequest);

        cartMapper.addItem(addCartItemEntity);
    }

    public void removeItem(DeleteItemFromCartRequest deleteItemFromCartRequest) {
        Long cartId = deleteItemFromCartRequest.getCartId();
        Long productId = deleteItemFromCartRequest.getProductId();
        Map<String, Object> removeItemMap = new HashMap<>();

        removeItemMap.put("cartId", cartId);
        removeItemMap.put("productId", productId);
        cartMapper.removeItem(removeItemMap);
    }

    public Long getQuantity(Long cartId) {
        return cartMapper.getQuantity(cartId);
    }

    public Long getTotalPoints(Long cartId) {
        return cartMapper.getTotalPoints(cartId);
    }

    public boolean isCartItemExists(AddItemToCartRequest addItemToCartRequest) {
        Long cartId = addItemToCartRequest.getCartId();
        Long productId = addItemToCartRequest.getProductId();
        return cartMapper.isCartItemExists(cartId, productId);
    }

    public void increaseQuantity(AddItemToCartRequest addItemToCartRequest) {
        Long cartId = addItemToCartRequest.getCartId();
        Long productId = addItemToCartRequest.getProductId();
        cartMapper.increaseQuantity(cartId, productId);
    }

    public void increaseTotalPrice(AddItemToCartRequest addItemToCartRequest) {
        Long cartId = addItemToCartRequest.getCartId();
        Long productId = addItemToCartRequest.getProductId();
        Long price = productService.getProductInfo(productId).getPrice();
        cartMapper.increaseTotalPrice(cartId, productId, price);
    }

    public void decreaseQuantity(DeleteItemFromCartRequest deleteItemFromCartRequest) {
        Long cartId = deleteItemFromCartRequest.getCartId();
        Long productId = deleteItemFromCartRequest.getProductId();
        cartMapper.decreaseQuantity(cartId, productId);
    }

    public void decreaseTotalPrice(DeleteItemFromCartRequest deleteItemFromCartRequest) {
        Long cartId = deleteItemFromCartRequest.getCartId();
        Long productId = deleteItemFromCartRequest.getProductId();
        Long price = productService.getProductInfo(productId).getPrice();
        cartMapper.decreaseTotalPrice(cartId, productId, price);
    }


    public void updateCart(Long cartId) {
        Long totalQuantity = getQuantity(cartId);
        if (totalQuantity == null) {
            totalQuantity = 0L;
        }

        Long totalPoints = getTotalPoints(cartId);
        if (totalPoints == null) {
            totalPoints = 0L;
        }

        cartMapper.updateCart(cartId, totalPoints, totalQuantity);
    }

    public void clearCart(Long cartId) {
        if (cartId == null) {
            return;
        }

        cartMapper.deleteCartItems(cartId);
        cartMapper.resetCartValues(cartId);
    }
}
