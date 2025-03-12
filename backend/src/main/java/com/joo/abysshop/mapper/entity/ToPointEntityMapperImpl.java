package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.point.CreatePointRechargeRequest;
import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import org.springframework.stereotype.Component;

@Component
public class ToPointEntityMapperImpl implements ToPointEntityMapper {

    @Override
    public CreatePointRechargeEntity toPointRechargeEntity(
        CreatePointRechargeRequest createPointRechargeRequest) {
        return CreatePointRechargeEntity.builder()
            .userId(createPointRechargeRequest.getUserId())
            .nickname(createPointRechargeRequest.getNickname())
            .points(createPointRechargeRequest.getPoints())
            .build();
    }

    @Override
    public UpdatePointRechargeDetailEntity toUpdatePointRechargeDetailEntity(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest) {
        return UpdatePointRechargeDetailEntity.builder()
            .rechargeDetailId(updatePointRechargeDetailRequest.getRechargeDetailId())
            .bank(updatePointRechargeDetailRequest.getBank())
            .accountNumber(updatePointRechargeDetailRequest.getAccountNumber())
            .build();
    }
}
