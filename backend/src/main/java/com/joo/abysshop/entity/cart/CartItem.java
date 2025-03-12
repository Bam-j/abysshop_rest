package com.joo.abysshop.entity.cart;

import com.joo.abysshop.entity.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Table(name = "cart_items_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", nullable = false)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_name")
    private String productName;

    @Check(constraints = "price >= 0")
    @Column(name = "price")
    private Long price;

    @Check(constraints = "quantity >= 0")
    @Column(name = "quantity", nullable = false)
    private Long quantity = 1L;

    @PrePersist
    public void prePersist() {
        if (this.quantity == null) {
            this.quantity = 1L;
        }
    }

    @Builder
    public CartItem(Product product, String productName, Long price, Long quantity) {
        this.product = product;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
