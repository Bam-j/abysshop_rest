package com.joo.abysshop.mapper.mybatis;

import com.joo.abysshop.entity.account.AccountEntity;
import com.joo.abysshop.entity.account.SignUpEntity;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByNickname(String nickname);

    void insertUser(SignUpEntity signUpEntity);

    void updateNickname(@Param("userId") Long userId, @Param("newNickname") String newNickname);

    void updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    void deleteUser(Long userId);
}
