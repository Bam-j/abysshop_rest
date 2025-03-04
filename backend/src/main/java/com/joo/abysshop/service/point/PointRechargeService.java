package com.joo.abysshop.service.point;

import com.joo.abysshop.dto.point.CreatePointRechargeRequest;
import com.joo.abysshop.entity.point.CreatePointRechargeEntity;
import com.joo.abysshop.mapper.entity.ToPointEntityMapper;
import com.joo.abysshop.mapper.mybatis.PointMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointRechargeService {

    private final ToPointEntityMapper toPointEntityMapper;
    private final PointMapper pointMapper;

    public void createPointRecharge(CreatePointRechargeRequest createPointRechargeRequest) {
        CreatePointRechargeEntity createPointRechargeEntity = toPointEntityMapper.toPointRechargeEntity(
            createPointRechargeRequest);
        pointMapper.insertPointRecharge(createPointRechargeEntity);
    }

    public void changePointRechargeState(Long rechargeId, String newState) {
        Map<String, Object> changeStateMap = new HashMap<>();
        changeStateMap.put("rechargeId", rechargeId);
        changeStateMap.put("newState", newState);

        pointMapper.changePointRechargeState(changeStateMap);
    }

    public int getTotalPointRechargeCount() {
        return pointMapper.countAllPointRecharges();
    }
}
