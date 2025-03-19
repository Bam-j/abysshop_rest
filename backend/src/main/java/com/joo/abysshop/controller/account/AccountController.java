package com.joo.abysshop.controller.account;

import com.joo.abysshop.dto.account.request.WithdrawAccountRequest;
import com.joo.abysshop.dto.user.request.UpdateNicknameRequest;
import com.joo.abysshop.dto.account.request.UpdatePasswordRequest;
import com.joo.abysshop.service.account.AccountCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountCommandService accountCommandService;

    @PatchMapping("/nickname")
    public ResponseEntity<Object> updateNickname(UpdateNicknameRequest updateNicknameRequest) {
        accountCommandService.updateNickname(updateNicknameRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Object> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        accountCommandService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<Object> withdraw(WithdrawAccountRequest withdrawAccountRequest,
        @RequestHeader("Authorization") String authorization) {
        String token = authorization.startsWith("Bearer ")
            ? authorization.substring(7) : authorization;
        accountCommandService.withdraw(withdrawAccountRequest, token);
        return ResponseEntity.ok().build();
    }
}
