package com.joo.abysshop.entity.order;

import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.util.enums.OrderState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Table(name = "orders_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "ordered_at", nullable = false, updatable = false)
    private LocalDateTime orderedAt;

    @Check(constraints = "total_price >= 0")
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "order_state", nullable = false)
    private OrderState orderState = OrderState.SHIPPING;

    @PrePersist
    public void prePersist() {
        if (this.orderedAt == null) {
            this.orderedAt = LocalDateTime.now();
        }

        if (this.orderState == null) {
            this.orderState = OrderState.SHIPPING;
        }
    }

    @Builder
    public Order(User user, LocalDateTime orderedAt, Long totalPrice, OrderState orderState) {
        this.user = user;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

    public void updateOrderState(String newState) {
        this.orderState = OrderState.valueOf(newState);
    }
}
