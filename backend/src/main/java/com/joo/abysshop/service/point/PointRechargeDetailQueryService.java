package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.admin.response.AdminPointRechargeDetailListResponse;
import com.joo.abysshop.entity.point.PointRechargeDetail;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointRechargeDetailQueryService {

    private final PointRechargeDetailRepository pointRechargeDetailRepository;

    public Page<AdminPointRechargeDetailListResponse> getPagedAdminPointRechargeDetailList(
        Pageable pageable) {
        return pointRechargeDetailRepository.findAll(pageable)
            .map(AdminPointRechargeDetailListResponse::new);
    }

    public PointRechargeDetail findById(Long pointRechargeDetailId) {
        return pointRechargeDetailRepository.findById(pointRechargeDetailId)
            .orElseThrow(() -> new EntityNotFoundException("해당 주문 상세 정보가 없습니다."));
    }
}
