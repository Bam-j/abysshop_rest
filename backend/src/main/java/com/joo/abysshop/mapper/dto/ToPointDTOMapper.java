package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.point.PointRechargeDetailListResponse;
import com.joo.abysshop.dto.point.PointRechargeListResponse;

public interface ToPointDTOMapper {

    PointRechargeListResponse toPointRechargeListResponse(PointRechargeEntity pointRechargeEntity);

    PointRechargeDetailListResponse toPointRechargeDetailResponse(
        PointRechargeDetailEntity pointRechargeDetailEntity, String nickname);
}
