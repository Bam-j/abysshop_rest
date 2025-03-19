package com.joo.abysshop.repository.cart;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.cart.CartItem;
import com.joo.abysshop.entity.product.Product;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    Long findQuantityByProduct(Product product);

    Optional<CartItem> findByProduct(Product product);

    @Query("SELECT SUM(ci.quantity * p.price)" +
        "FROM CartItem ci " +
        "JOIN ci.product p " +
        "WHERE ci.cart.cartId = :cartId AND p.productId = :productId")
    Long findTotalPriceByCartIdAndProductId(@Param("cartId") Long cartId,
        @Param("productId") Long productId);

    @Query("SELECT COALESCE(SUM(ci.quantity), 0) AS quantity FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    Long findTotalQuantityByCartId(Long cartId);
}
