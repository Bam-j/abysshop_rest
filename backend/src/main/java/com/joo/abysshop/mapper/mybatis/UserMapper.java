package com.joo.abysshop.mapper.mybatis;

import com.joo.abysshop.entity.user.UserEntity;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Optional<UserEntity> findByUserId(Long userId);

    Long getUserPoints(Long userId);

    void consumePoint(Map<String, Object> remainPointsMap);

    Optional<UserEntity> findByUsername(String username);

    String getNickname(Long userId);

    String getUserType(Long userId);
}
