package com.joo.abysshop.entity.point;

import com.joo.abysshop.util.security.CryptoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Table(name = "point_recharge_details_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRechargeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_recharge_detail_id", nullable = false)
    private Long pointRechargeDetailId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_recharge_id", referencedColumnName = "point_recharge_id", nullable = false)
    private PointRecharge pointRecharge;

    @Convert(converter = CryptoConverter.class)
    @Column(name = "bank")
    private String bank;

    @Convert(converter = CryptoConverter.class)
    @Column(name = "account_number")
    private String accountNumber;

    @Check(constraints = "deposit_amount >= 0")
    @Column(name = "deposit_amount")
    private Long depositAmount;

    @Column(name = "deposit_confirmed_at")
    private LocalDateTime depositConfirmedAt;

    @Builder
    public PointRechargeDetail(
        PointRecharge pointRecharge,
        String bank,
        String accountNumber,
        Long depositAmount,
        LocalDateTime depositConfirmedAt) {
        this.pointRecharge = pointRecharge;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositConfirmedAt = depositConfirmedAt;
    }

    public void updatePointRechargeDetail(
        String bank,
        String accountNumber,
        Long depositAmount,
        LocalDateTime depositConfirmedAt
    ) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.depositConfirmedAt = depositConfirmedAt;
    }
}
