package com.joo.abysshop.repository.cart;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c.cartId FROM Cart c WHERE c.user.userId = :userId")
    Long findCartIdByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = "cartItems")
    Optional<Cart> findByUser(User user);

    @Override
    @NonNull
    @EntityGraph(attributePaths = "cartItems")
    List<Cart> findAll();

    Long user(User user);

    @Query("SELECT c.totalQuantity FROM Cart c WHERE c.cartId = :cartId")
    Long getTotalQuantityByCartId(@Param("cartId") Long cartId);
}
