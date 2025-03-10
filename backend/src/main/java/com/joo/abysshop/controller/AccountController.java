package com.joo.abysshop.controller;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.constants.RedirectMappings;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.dto.account.ChangeNicknameRequest;
import com.joo.abysshop.dto.account.ChangePasswordRequest;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.account.AccountService;
import com.joo.abysshop.service.product.ProductService;
import com.joo.abysshop.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PatchMapping("/nickname")
    public ResponseEntity<Object> updateNickname(ChangeNicknameRequest changeNicknameRequest) {
        ResultStatus updateNicknameResult = accountService.updateNickname(changeNicknameRequest);

        if (updateNicknameResult.equals(ResultStatus.SUCCESS)) {
            return ResponseEntity.ok().build();
        } else if (updateNicknameResult.equals(ResultStatus.SAME_NICKNAME)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "동일한 닉네임으로의 변경은 불가능합니다."));
        } else if (updateNicknameResult.equals(ResultStatus.DUPLICATE_NICKNAME)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(Messages.FAILURE_MESSAGE, "이미 존재하는 닉네임입니다."));
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(Messages.FAILURE_MESSAGE, "로그인 요청에 실패하였습니다."));
        }
    }

    @PostMapping("/change/nickname")
    public RedirectView changeNickname(@ModelAttribute ChangeNicknameRequest changeNicknameRequest,
        RedirectAttributes redirectAttributes) {
        ResultStatus changeNicknameResult = accountService.changeNickname(changeNicknameRequest);

        Long userId = changeNicknameRequest.getUserId();
        String url = "/user/my-page/" + userId + "?menu=user-info";

        if (changeNicknameResult == ResultStatus.SUCCESS) {
            return new RedirectView(url);
        } else if (changeNicknameResult == ResultStatus.BAD_REQUEST) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "잘못된 요청입니다.");
            return new RedirectView(url);
        } else if (changeNicknameResult == ResultStatus.SAME_NICKNAME) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE,
                "동일한 닉네임으로의 변경은 불가능합니다.");
            return new RedirectView(url);
        } else if (changeNicknameResult == ResultStatus.EMPTY_INPUT_FORM) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "공백은 허용되지 않습니다.");
            return new RedirectView(url);
        } else if (changeNicknameResult == ResultStatus.DUPLICATE_NICKNAME) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "이미 사용중인 닉네임입니다.");
            return new RedirectView(url);
        } else {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "처리 오류");
            return new RedirectView(url);
        }
    }

    @PostMapping("/account/change/password")
    public RedirectView changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest,
        RedirectAttributes redirectAttributes) {
        ResultStatus changePasswordResult = accountService.changePassword(changePasswordRequest);

        Long userId = changePasswordRequest.getUserId();
        String url = "/user/my-page/" + userId + "?menu=user-info";

        if (changePasswordResult == ResultStatus.SUCCESS) {
            return new RedirectView(url);
        } else if (changePasswordResult == ResultStatus.BAD_REQUEST) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "잘못된 요청입니다.");
            return new RedirectView(url);
        } else if (changePasswordResult == ResultStatus.SAME_PASSWORD) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE,
                "동일한 패스워드로의 변경은 불가능합니다.");
            return new RedirectView(url);
        } else if (changePasswordResult == ResultStatus.EMPTY_INPUT_FORM) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "공백은 허용되지 않습니다.");
            return new RedirectView(url);
        } else {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "처리 오류");
            return new RedirectView(url);
        }
    }

    /* 로그아웃은 클라이언트 측에서 JWT 토큰 삭제하여 구현
    @PostMapping("/account/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return RedirectMappings.REDIRECT_INDEX;
    }
     */

    @PostMapping("/account/withdraw")
    public RedirectView withdraw(@ModelAttribute AccountWithdrawRequest accountWithdrawRequest,
        HttpSession session, Model model) {
        ResultStatus withdrawResult = accountService.withdraw(accountWithdrawRequest);

        if (withdrawResult == ResultStatus.SUCCESS) {
            session.invalidate();
            return new RedirectView(ViewNames.INDEX_PAGE);
        } else {
            Long userId = accountWithdrawRequest.getUserId();
            String url = "/user/my-page/" + userId + "?menu=user-info";
            return new RedirectView(url);
        }
    }
}
