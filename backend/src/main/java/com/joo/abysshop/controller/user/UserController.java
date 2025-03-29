package com.joo.abysshop.controller.user;

import com.joo.abysshop.dto.user.response.UserInfoResponse;
import com.joo.abysshop.service.user.UserQueryService;
import com.joo.abysshop.util.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserQueryService userQueryService;

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUser(
        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserInfoResponse userInfoResponse = userQueryService
            .getUserInfo(customUserDetails.getUserId());
        return ResponseEntity.ok(userInfoResponse);
    }
}
