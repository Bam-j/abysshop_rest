package com.joo.abysshop.repository.cart;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.cart.CartItem;
import com.joo.abysshop.entity.product.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @EntityGraph(attributePaths = "product")
    List<CartItem> findAllByCart(Cart cart);

    void deleteAllByCart(Cart cart);

    @EntityGraph(attributePaths = {"cart", "product"})
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    void deleteByCartAndProduct(Cart cart, Product product);

    @Query("SELECT c.quantity FROM CartItem c WHERE c.product = :product")
    Long findQuantityByProduct(Product product);

    @EntityGraph(attributePaths = {"product"})
    Optional<CartItem> findByProduct(Product product);

    @Query("SELECT SUM(ci.quantity * p.price)" +
        "FROM CartItem ci " +
        "JOIN ci.product p " +
        "WHERE ci.cart.cartId = :cartId AND p.productId = :productId")
    Long findTotalPriceByCartIdAndProductId(@Param("cartId") Long cartId,
        @Param("productId") Long productId);

    @Query("SELECT SUM(ci.quantity * p.price) " +
        "FROM CartItem ci " +
        "JOIN ci.product p " +
        "WHERE ci.cart.cartId = :cartId")
    Long findTotalPriceByCartId(@Param("cartId") Long cartId);

    @Query("SELECT COALESCE(SUM(ci.quantity), 0) AS quantity FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    Long findTotalQuantityByCartId(Long cartId);
}
