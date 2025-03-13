package com.joo.abysshop.repository.cart;

import com.joo.abysshop.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c.cartId FROM Cart c WHERE c.user.userId = :userId")
    Long findCartIdByUserId(Long userId);
}
