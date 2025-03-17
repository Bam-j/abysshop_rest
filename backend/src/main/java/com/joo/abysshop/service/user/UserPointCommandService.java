package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.point.request.CreatePointRechargeRequest;
import com.joo.abysshop.entity.point.PointRecharge;
import com.joo.abysshop.entity.user.User;
import com.joo.abysshop.factory.PointRechargeFactory;
import com.joo.abysshop.repository.point.PointRechargeRepository;
import com.joo.abysshop.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPointCommandService {

    private final PointRechargeRepository pointRechargeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createPointRecharge(CreatePointRechargeRequest createPointRechargeRequest) {
        User user = userRepository.findById(createPointRechargeRequest.userId())
            .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        PointRecharge pointRecharge = PointRechargeFactory.of(user,
            createPointRechargeRequest.requestedPoints());
        pointRechargeRepository.save(pointRecharge);
    }
}
