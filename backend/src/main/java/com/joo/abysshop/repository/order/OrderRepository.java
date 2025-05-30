package com.joo.abysshop.repository.order;

import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.entity.user.User;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "user")
    Page<Order> findByUser(User user, Pageable pageable);

    @Override
    @NonNull
    @EntityGraph(attributePaths = "user")
    Page<Order> findAll(@NonNull Pageable pageable);

    @Modifying
    @Transactional
    void deleteByOrderedAt(LocalDateTime cutoff);
}
