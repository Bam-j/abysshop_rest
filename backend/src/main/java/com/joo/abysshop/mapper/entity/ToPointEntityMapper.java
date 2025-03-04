package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.point.CreatePointRechargeRequest;
import com.joo.abysshop.dto.point.UpdatePointRechargeDetailRequest;
import com.joo.abysshop.entity.point.CreatePointRechargeEntity;
import com.joo.abysshop.entity.point.UpdatePointRechargeDetailEntity;

public interface ToPointEntityMapper {

    CreatePointRechargeEntity toPointRechargeEntity(
        CreatePointRechargeRequest createPointRechargeRequest);

    UpdatePointRechargeDetailEntity toUpdatePointRechargeDetailEntity(
        UpdatePointRechargeDetailRequest updatePointRechargeDetailRequest);
}
