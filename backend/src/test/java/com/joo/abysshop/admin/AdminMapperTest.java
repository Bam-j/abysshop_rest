package com.joo.abysshop.admin;

import com.joo.abysshop.entity.user.UserEntity;
import com.joo.abysshop.mapper.mybatis.AdminMapper;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    @DisplayName("유저에게 포인트 지급 mapper 테스트")
    void providePointTest() {
        /*
        * 홀수번 더미데이터 포인트 10000, 짝수번 더미데이터 포인트 20000
         */
        //given

        //when
        adminMapper.providePoint(1L, 10000L);

        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(1L);
        UserEntity userEntity = optionalUserEntity.get();

        Long totalPointAfterProvided = userEntity.getPoints();

        //then
        assertThat(totalPointAfterProvided).isEqualTo(20000L);
    }
}