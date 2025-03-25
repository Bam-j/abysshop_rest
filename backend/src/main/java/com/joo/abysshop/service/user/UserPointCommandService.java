package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.point.request.CreatePointRechargeRequest;
import com.joo.abysshop.dto.point.request.DeductPointsRequest;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.entity.point.PointRechargeDetail;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.factory.PointRechargeFactory;
import com.joo.abysshop.repository.point.PointRechargeDetailRepository;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.exception.point.InsufficientPointBalanceException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPointCommandService {

    private final PointRechargeRepository pointRechargeRepository;
    private final PointRechargeDetailRepository pointRechargeDetailRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPointRecharge(CreatePointRechargeRequest createPointRechargeRequest) {
        User user = userRepository.findById(createPointRechargeRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        PointRecharge pointRecharge = PointRechargeFactory.of(user,
            createPointRechargeRequest.requestedPoints());
        pointRechargeRepository.save(pointRecharge);

        PointRechargeDetail detail = PointRechargeDetail.builder()
            .pointRecharge(pointRecharge)
            .build();
        pointRechargeDetailRepository.save(detail);
    }

    @Transactional
    public void deductPoints(DeductPointsRequest deductPointsRequest) {
        User user = userRepository.findById(deductPointsRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        Long userPointBalance = user.getPointBalance();

        if (userPointBalance < deductPointsRequest.orderTotalPrice()) {
            throw new InsufficientPointBalanceException("포인트 잔액이 부족하여 결제에 실패했습니다.");
        }

        user.deductPointBalance(deductPointsRequest.orderTotalPrice());
    }
}
