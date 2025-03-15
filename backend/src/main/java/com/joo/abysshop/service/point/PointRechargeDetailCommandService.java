package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.entity.point.PointRechargeDetail;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointRechargeDetailCommandService {

    private final PointRechargeDetailRepository pointRechargeDetailRepository;
    private final PointRechargeDetailQueryService pointRechargeDetailQueryService;

    @Transactional
    public void updatePointRechargeDetail(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        PointRechargeDetail pointRechargeDetail = pointRechargeDetailQueryService.findById(
            updatePointRechargeDetailRequest.pointRechargeDetailId());
        pointRechargeDetail.updatePointRechargeDetail(
            updatePointRechargeDetailRequest.bank(),
            updatePointRechargeDetailRequest.accountNumber(),
            updatePointRechargeDetailRequest.depositAmount(),
            pointRechargeDetail.getDepositConfirmedAt() == null ? LocalDateTime.now()
                : pointRechargeDetail.getDepositConfirmedAt()
        );
    }
}
