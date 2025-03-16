package com.joo.abysshop.repository.cart;

import com.joo.abysshop.entity.cart.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByCartId(Long cartId);

    void deleteAllByCartId(Long cartId);
}
