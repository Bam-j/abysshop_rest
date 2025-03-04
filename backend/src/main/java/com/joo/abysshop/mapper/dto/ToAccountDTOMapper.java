package com.joo.abysshop.mapper.dto;

import com.joo.abysshop.dto.account.AccountSignInResponse;
import com.joo.abysshop.entity.account.SignInEntity;

public interface ToAccountDTOMapper {

    AccountSignInResponse toAccountSignInResponse(SignInEntity signInEntity);
}
