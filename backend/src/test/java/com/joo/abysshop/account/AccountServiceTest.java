package com.joo.abysshop.account;

import com.joo.abysshop.dto.account.AccountSignInRequest;
import com.joo.abysshop.dto.account.AccountSignUpRequest;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.account.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    void signUpTest() {
        //given
        AccountSignUpRequest accountSignUpRequest = AccountSignUpRequest.builder()
            .username("test")
            .nickname("test")
            .password("test")
            .build();

        //when
        ResultStatus signUpResult = accountService.signUp(accountSignUpRequest);

        //then
        assertThat(signUpResult).isEqualTo(ResultStatus.SUCCESS);
    }

    @Test
    @Transactional
    @DisplayName("회원가입 실패 테스트")
    void signUpFailureTest() {
        //given
        AccountSignUpRequest accountSignUpRequest = AccountSignUpRequest.builder()
            .username("user1")
            .nickname("user1")
            .password("0000")
            .build();

        //when
        ResultStatus signUpResult = accountService.signUp(accountSignUpRequest);

        //then
        assertThat(signUpResult).isEqualTo(ResultStatus.FAILURE);
    }

    @Test
    @Transactional
    @DisplayName("로그인 테스트")
    void signInTest() {
        //given
        //로그인 테스트 계정(id, username, nick, pw): 1, "user1", "user1", "0000"
        AccountSignInRequest accountSignInRequest = AccountSignInRequest.builder()
            .username("user1")
            .password("0000")
            .build();

        //when
        ResultStatus signInResult = accountService.signIn(accountSignInRequest);

        //then
        assertThat(signInResult).isEqualTo(ResultStatus.SUCCESS);
    }

    @Test
    @Transactional
    @DisplayName("계정 탈퇴 테스트")
    void withdrawTest() {
        //given
        //삭제에 사용된 테스트 더미데이터 userId, password: 1L, "0000"
        AccountWithdrawRequest wrongPasswordRequest = AccountWithdrawRequest.builder()
            .userId(1L)
            .password("hello")
            .build();
        AccountWithdrawRequest matchPasswordRequest = AccountWithdrawRequest.builder()
            .userId(1L)
            .password("0000")
            .build();

        //when
        ResultStatus failureResult = accountService.withdraw(wrongPasswordRequest);
        ResultStatus successResult = accountService.withdraw(matchPasswordRequest);

        //then
        assertThat(failureResult).isEqualTo(ResultStatus.FAILURE);
        assertThat(successResult).isEqualTo(ResultStatus.SUCCESS);
    }
}