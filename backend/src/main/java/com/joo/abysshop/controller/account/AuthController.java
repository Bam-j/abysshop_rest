package com.joo.abysshop.controller.account;

import com.joo.abysshop.dto.account.request.SignInRequest;
import com.joo.abysshop.dto.account.request.SignUpRequest;
import com.joo.abysshop.service.account.AuthCommandService;
import com.joo.abysshop.service.account.AuthQueryService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthQueryService authQueryService;
    private final AuthCommandService authCommandService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> authenticateUser(SignInRequest signInRequest) {
        String token = authQueryService.authenticateUser(signInRequest);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> createUser(SignUpRequest signUpRequest) {
        authCommandService.createUser(signUpRequest);
        return ResponseEntity.ok().build();
    }
}