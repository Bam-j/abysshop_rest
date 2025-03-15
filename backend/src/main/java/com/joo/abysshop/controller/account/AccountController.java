package com.joo.abysshop.controller.account;

import com.joo.abysshop.dto.account.request.WithdrawAccountRequest;
import com.joo.abysshop.dto.user.request.UpdateNicknameRequest;
import com.joo.abysshop.dto.account.request.UpdatePasswordRequest;
import com.joo.abysshop.service.account.AccountService;
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

    private final AccountService accountService;

    @PatchMapping("/nickname")
    public ResponseEntity<Object> updateNickname(UpdateNicknameRequest updateNicknameRequest) {
        accountService.updateNickname(updateNicknameRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Object> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        accountService.updatePassword(updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<Object> withdraw(WithdrawAccountRequest withdrawAccountRequest,
        @RequestHeader("Authorization") String authorization) {
        String token = authorization.startsWith("Bearer ")
            ? authorization.substring(7) : authorization;
        accountService.withdraw(withdrawAccountRequest, token);
        return ResponseEntity.ok().build();
    }
}
