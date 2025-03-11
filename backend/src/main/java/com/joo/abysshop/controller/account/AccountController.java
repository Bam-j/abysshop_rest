package com.joo.abysshop.controller.account;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.dto.account.UpdateNicknameRequest;
import com.joo.abysshop.dto.account.UpdatePasswordRequest;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.account.AccountService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PatchMapping("/nickname")
    public ResponseEntity<Object> updateNickname(UpdateNicknameRequest updateNicknameRequest) {
        ResultStatus updateNicknameResult = accountService.updateNickname(updateNicknameRequest);

        if (updateNicknameResult.equals(ResultStatus.SUCCESS)) {
            //토큰 재발급으로 사용자 정보 갱신
            return ResponseEntity.noContent().build();
        } else if (updateNicknameResult.equals(ResultStatus.SAME_NICKNAME)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "동일한 닉네임으로의 변경은 불가능합니다."));
        } else if (updateNicknameResult.equals(ResultStatus.DUPLICATE_NICKNAME)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "이미 존재하는 닉네임입니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "닉네임 변경 요청에 실패하였습니다."));
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<Object> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        ResultStatus updatePasswordResult = accountService.updatePassword(updatePasswordRequest);

        if (updatePasswordResult.equals(ResultStatus.SUCCESS)) {
            //토큰 재발급으로 사용자 정보 갱신
            return ResponseEntity.noContent().build();
        } else if (updatePasswordResult.equals(ResultStatus.SAME_PASSWORD)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "동일한 비밀번호로의 변경은 불가능합니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "비밀번호 변경 요청에 실패하였습니다."));
        }
    }

    /* 로그아웃은 클라이언트 측에서 JWT 토큰 삭제하여 구현 -> 나중에 구현되면 이 부분 삭제
    @PostMapping("/account/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return RedirectMappings.REDIRECT_INDEX;
    }
     */

    @DeleteMapping("/withdraw")
    public ResponseEntity<Object> withdraw(AccountWithdrawRequest accountWithdrawRequest) {
        ResultStatus withdrawResult = accountService.withdraw(accountWithdrawRequest);

        if (withdrawResult.equals(ResultStatus.SUCCESS)) {
            //jwt 인증 해제, 프론트에서 ok 응답 받음 확인하고 INDEX로 이동
            return ResponseEntity.noContent().build();
        } else if (withdrawResult.equals(ResultStatus.INVALID_PASSWORD)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(Messages.FAILURE_MESSAGE, "비밀번호가 일치하지 않습니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "회원 탈퇴 요청에 실패하였습니다."));
        }
    }
}
