package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.account.AccountSignInResponse;
import com.joo.abysshop.entity.account.SignInEntity;
import org.springframework.stereotype.Component;

@Component
public class ToAccountDTOMapperImpl implements ToAccountDTOMapper {

    @Override
    public AccountSignInResponse toAccountSignInResponse(SignInEntity signInEntity) {
        return AccountSignInResponse.builder()
            .password(signInEntity.getPassword())
            .build();
    }
}
