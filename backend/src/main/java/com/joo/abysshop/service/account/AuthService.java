package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.SignInRequest;
import com.joo.abysshop.dto.account.SignUpRequest;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.enums.ResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public ResultStatus authenticateUser(SignInRequest signInRequest) {
        /*
         *  1. users_table에 username이 존재하는지 확인 > 실패: INVALID_USERNAME
         *  2. 일치하는 username과 입력받은 password가 일치하는지 확인 > 실패: INVALIDPASSWORD
         *  3. 사용자 정보(userId, cartId, nickname, userType, pointBlance) JWT 인증 토큰 발급
         */
    }

    public ResultStatus createUser(SignUpRequest signUpRequest) {
        /*
         *  1. users_table에 username, nickname이 존재하는지 확인 > 실패: DUPLICATE_USERNAME, DUPLICATE_NICKNAME
         *  2. 1번 통과시 users_table에 새 회원 create
         *  !저장시 비밀번호 암호화 작업
         */
    }
}
