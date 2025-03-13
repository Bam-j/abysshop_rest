package com.joo.abysshop.controller.account;

import com.joo.abysshop.dto.account.SignInRequest;
import com.joo.abysshop.dto.account.SignUpRequest;
import com.joo.abysshop.service.account.AuthService;
import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.enums.ResultStatus;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> authenticateUser(SignInRequest signInRequest) {
        ResultStatus signInResult = authService.authenticateUser(signInRequest);

        if (signInResult.equals(ResultStatus.SUCCESS)) {
            //success시 서비스 레이어에서 jwt 토큰 발급 하고 여기서는 토큰과 유저 정보만 던져주기
            return ResponseEntity.noContent().build();
        } else if (signInResult.equals(ResultStatus.INVALID_USERNAME)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "존재하지 않는 계정입니다."));
        } else if (signInResult.equals(ResultStatus.INVALID_PASSWORD)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "잘못된 패스워드입니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "로그인 요청에 실패하였습니다."));
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> createUser(SignUpRequest signUpRequest) {
        ResultStatus signUpResult = authService.createUser(signUpRequest);

        if (signUpResult.equals(ResultStatus.SUCCESS)) {
            //프론트엔드에서 sign-in 페이지로 redirect되도록 처리
            return ResponseEntity.noContent().build();
        } else if (signUpResult.equals(ResultStatus.DUPLICATE_USERNAME)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "이미 존재하는 계정입니다."));
        } else if (signUpResult.equals(ResultStatus.DUPLICATE_NICKNAME)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "이미 존재하는 닉네임입니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "로그인 요청에 실패하였습니다."));
        }
    }
}