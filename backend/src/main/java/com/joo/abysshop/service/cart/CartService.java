package com.joo.abysshop.service.cart;

import com.joo.abysshop.dto.cart.AddItemRequest;
import com.joo.abysshop.dto.cart.CartItemResponse;
import com.joo.abysshop.dto.cart.CartResponse;
import com.joo.abysshop.dto.cart.RemoveItemRequest;
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

    public void addItem(AddItemRequest addItemRequest) {
        Long productId = addItemRequest.getProductId();
        ProductInfoRequest productInfoRequest = productService.getProductInfo(productId);
        AddCartItemEntity addCartItemEntity = toCartEntityMapper.toAddCartItemEntity(addItemRequest,
            productInfoRequest);

        cartMapper.addItem(addCartItemEntity);
    }

    public void removeItem(RemoveItemRequest removeItemRequest) {
        Long cartId = removeItemRequest.getCartId();
        Long productId = removeItemRequest.getProductId();
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

    public boolean isCartItemExists(AddItemRequest addItemRequest) {
        Long cartId = addItemRequest.getCartId();
        Long productId = addItemRequest.getProductId();
        return cartMapper.isCartItemExists(cartId, productId);
    }

    public void increaseQuantity(AddItemRequest addItemRequest) {
        Long cartId = addItemRequest.getCartId();
        Long productId = addItemRequest.getProductId();
        cartMapper.increaseQuantity(cartId, productId);
    }

    public void increaseTotalPrice(AddItemRequest addItemRequest) {
        Long cartId = addItemRequest.getCartId();
        Long productId = addItemRequest.getProductId();
        Long price = productService.getProductInfo(productId).getPrice();
        cartMapper.increaseTotalPrice(cartId, productId, price);
    }

    public void decreaseQuantity(RemoveItemRequest removeItemRequest) {
        Long cartId = removeItemRequest.getCartId();
        Long productId = removeItemRequest.getProductId();
        cartMapper.decreaseQuantity(cartId, productId);
    }

    public void decreaseTotalPrice(RemoveItemRequest removeItemRequest) {
        Long cartId = removeItemRequest.getCartId();
        Long productId = removeItemRequest.getProductId();
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
