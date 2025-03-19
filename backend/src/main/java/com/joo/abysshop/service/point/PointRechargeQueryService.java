package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.admin.response.AdminPointRechargeListResponse;
import com.joo.abysshop.dto.user.response.UserPointRechargeListResponse;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import com.joo.abysshop.repository.user.UserRepository;
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
    private final UserRepository userRepository;

    public Page<AdminPointRechargeListResponse> getPagedAdminPointRechargeList(Pageable pageable) {
        return pointRechargeRepository.findAll(pageable).map(AdminPointRechargeListResponse::new);
    }

    public PointRecharge findById(Long pointRechargeId) {
        return pointRechargeRepository.findById(pointRechargeId)
            .orElseThrow(() -> new EntityNotFoundException("해당 포인트 충전 요청이 존재하지 않습니다."));
    }

    public Page<UserPointRechargeListResponse> getPagedUserPointRechargeList(Long userId,
        Pageable pageable) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
        return pointRechargeRepository.findByUser(user, pageable)
            .map(UserPointRechargeListResponse::new);
    }
}
