package com.joo.abysshop.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class AccountEntity {

    String username;
    String nickname;
    String password;
}
