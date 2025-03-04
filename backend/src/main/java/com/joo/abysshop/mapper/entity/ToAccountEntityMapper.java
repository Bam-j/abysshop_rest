package com.joo.abysshop.mapper.entity;

import com.joo.abysshop.dto.account.AccountSignUpRequest;
import com.joo.abysshop.entity.account.SignUpEntity;

public interface ToAccountEntityMapper {

    SignUpEntity toSignUpEntity(AccountSignUpRequest accountSignUpRequest, String encryptedPassword);
}
