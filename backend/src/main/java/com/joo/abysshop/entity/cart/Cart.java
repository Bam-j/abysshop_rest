package com.joo.abysshop.entity.cart;

import com.joo.abysshop.entity.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Table(name = "carts_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, unique = true)
    private User user;

    @Check(constraints = "total_quantity >= 0")
    @Column(name = "total_quantity")
    private Long totalQuantity = 0L;

    @Check(constraints = "total_price >= 0")
    @Column(name = "total_price")
    private Long totalPrice = 0L;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @PrePersist
    public void prePersist() {
        if (this.totalQuantity == null) {
            this.totalQuantity = 0L;
        }

        if (this.totalPrice == null) {
            this.totalPrice = 0L;
        }
    }

    @Builder
    public Cart(User user, Long totalQuantity, Long totalPrice) {
        this.user = user;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public void clearCart() {
        this.totalQuantity = 0L;
        this.totalPrice = 0L;
    }

    public void deleteItemFromCart(Long itemQuantity, Long itemTotalPrice) {
        this.totalQuantity -= itemQuantity;
        this.totalPrice -= itemTotalPrice;
    }

    public void updateCart(Long totalQuantity, Long totalPrice) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;

        if (this.totalQuantity == null) {
            this.totalQuantity = 0L;
        }

        if (this.totalPrice == null) {
            this.totalPrice = 0L;
        }
    }
}
