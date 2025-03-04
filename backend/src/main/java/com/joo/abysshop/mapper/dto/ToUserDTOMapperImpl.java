package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.entity.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ToUserDTOMapperImpl implements ToUserDTOMapper {

    @Override
    public UserInfoResponse toUserInfoResponse(UserEntity userEntity, Long cartId) {
        return UserInfoResponse.builder()
            .userId(userEntity.getUserId())
            .cartId(cartId)
            .username(userEntity.getUsername())
            .nickname(userEntity.getNickname())
            .userType(userEntity.getUserType())
            .points(userEntity.getPoints())
            .build();
    }
}
