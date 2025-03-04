package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.entity.user.UserEntity;

public interface ToUserDTOMapper {

    UserInfoResponse toUserInfoResponse(UserEntity userEntity, Long cartId);
}
