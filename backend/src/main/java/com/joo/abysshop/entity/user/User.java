package com.joo.abysshop.entity.user;

import com.joo.abysshop.entity.cart.Cart;
import com.joo.abysshop.entity.order.Order;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.util.enums.UserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Table(name = "users_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType = UserType.USER;

    @Check(constraints = "point_balance >= 0")
    @Column(name = "point_balance", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long pointBalance = 0L;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PointRecharge> pointRecharges = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.userType == null) {
            this.userType = UserType.USER;
        }

        if (this.pointBalance == null) {
            this.pointBalance = 0L;
        }
    }

    @Builder
    public User(String username, String nickname, String password, Long pointBalance) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.pointBalance = pointBalance;
    }

    public void setUserCart(Cart cart) {
        this.cart = cart;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void providePointBalance(Long points) {
        this.pointBalance += points;
    }

    public void deductPointBalance(Long points) {
        this.pointBalance -= points;
    }
}
