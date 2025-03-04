package com.joo.abysshop.mapper.mybatis;

import com.joo.abysshop.entity.cart.AddCartItemEntity;
import com.joo.abysshop.entity.cart.CartEntity;
import com.joo.abysshop.entity.cart.CartItemEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {

    CartEntity getCart(Long userId);

    List<CartItemEntity> getCartItems(Long cartId);

    void removeItem(Map<String, Object> removeItemMap);

    Long getQuantity(Long cartId);

    Long getTotalPoints(Long cartId);

    Long getCartIdByUserId(Long userId);

    void addItem(AddCartItemEntity addCartItemEntity);

    //mappper dto > entity로 변환 과정이 굳이 필요하지 않다 판단되어서 @Param 사용
    boolean isCartItemExists(@Param("cartId") Long cartId, @Param("productId") Long productId);

    void increaseQuantity(@Param("cartId") Long cartId, @Param("productId") Long productId);

    void increaseTotalPrice(@Param("cartId") Long cartId, @Param("productId") Long productId,
        @Param("price") Long price);

    Long getItemQuantity(@Param("cartId") Long cartId, @Param("productId") Long productId);

    void decreaseQuantity(@Param("cartId") Long cartId, @Param("productId") Long productId);

    void decreaseTotalPrice(@Param("cartId") Long cartId, @Param("productId") Long productId,
        @Param("price") Long price);

    void updateCart(@Param("cartId") Long cartId, @Param("totalPoints") Long totalPoints,
        @Param("totalQuantity") Long totalQuantity);

    void deleteCartItems(Long cartId);

    void resetCartValues(Long cartId);
}
