package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.user.UserInfoResponse;

public interface ToUserDTOMapper {

    UserInfoResponse toUserInfoResponse(UserEntity userEntity, Long cartId);
}
