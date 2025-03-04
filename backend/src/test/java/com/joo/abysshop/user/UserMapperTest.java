package com.joo.abysshop.user;

import com.joo.abysshop.entity.user.UserEntity;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("findByUserId Mapper 테스트")
    void findByUserIdTest() {
        //given
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(1L);

        //when
        Boolean isPresentResult = optionalUserEntity.isPresent();

        //then
        assertThat(isPresentResult).isTrue();
        System.out.println(optionalUserEntity);
    }
}