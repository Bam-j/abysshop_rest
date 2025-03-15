package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.point.AdminPointRechargeListResponse;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointRechargeQueryService {

    private final PointRechargeRepository pointRechargeRepository;

    public Page<AdminPointRechargeListResponse> getPagedAdminPointRechargeList(Pageable pageable) {
        return pointRechargeRepository.findAll(pageable).map(AdminPointRechargeListResponse::new);
    }

    public PointRecharge findById(Long pointRechargeId) {
        return pointRechargeRepository.findById(pointRechargeId)
            .orElseThrow(() -> new EntityNotFoundException("해당 포인트 충전 요청이 존재하지 않습니다."));
    }
}
