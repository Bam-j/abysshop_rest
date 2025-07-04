package com.joo.abysshop.entity.point;

import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.util.enums.RechargeState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "point_recharges_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRecharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_recharge_id", nullable = false)
    private Long pointRechargeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Check(constraints = "requested_points >= 0")
    @Column(name = "requested_points", nullable = false)
    private Long requestedPoints;

    @Column(name = "requested_at", nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "recharge_state", nullable = false)
    private RechargeState rechargeState = RechargeState.PENDING_PAYMENT;

    @PrePersist
    void  prePersist() {
        if (requestedAt == null) {
            requestedAt = LocalDateTime.now();
        }

        if (rechargeState == null) {
            rechargeState = RechargeState.PENDING_PAYMENT;
        }
    }

    @Builder
    public PointRecharge(User user, Long requestedPoints, LocalDateTime requestedAt,
        RechargeState rechargeState) {
        this.user = user;
        this.requestedPoints = requestedPoints;
        this.requestedAt = requestedAt;
        this.rechargeState = rechargeState;
    }

    public void updatePointRechargeState(String newState) {
        this.rechargeState = RechargeState.valueOf(newState);
    }
}
