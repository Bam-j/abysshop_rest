package com.joo.abysshop.controller;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.constants.RedirectMappings;
import com.joo.abysshop.util.constants.ViewNames;
import com.joo.abysshop.dto.account.AccountSignInRequest;
import com.joo.abysshop.dto.account.AccountSignUpRequest;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.dto.account.ChangeNicknameRequest;
import com.joo.abysshop.dto.account.ChangePasswordRequest;
import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.service.account.AccountService;
import com.joo.abysshop.service.product.ProductService;
import com.joo.abysshop.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/account/sign-in")
    public String getSignInPage() {
        return ViewNames.SIGN_IN_PAGE;
    }

    @PostMapping("/account/sign-in")
    public String signIn(@ModelAttribute AccountSignInRequest accountSignInRequest,
        HttpSession session, RedirectAttributes redirectAttributes) {
        ResultStatus signInResult = accountService.signIn(accountSignInRequest);

        if (signInResult == ResultStatus.SUCCESS) {
            UserInfoResponse userInfo = userService.getUserInfo(accountSignInRequest.getUsername());
            session.setAttribute("user", userInfo);
            return RedirectMappings.REDIRECT_INDEX;
        } else if (signInResult == ResultStatus.INVALID_USERNAME) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "존재하지 않는 계정입니다.");
            return RedirectMappings.REDIRECT_SIGN_IN;
        } else if (signInResult == ResultStatus.INVALID_PASSWORD) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "패스워드가 일치하지 않습니다.");
            return RedirectMappings.REDIRECT_SIGN_IN;
        } else {
            return RedirectMappings.REDIRECT_SIGN_IN;
        }
    }

    @GetMapping("/account/sign-up")
    public String getSignUpPage() {
        return ViewNames.SIGN_UP_PAGE;
    }

    @PostMapping("/account/sign-up")
    public String signUp(@ModelAttribute AccountSignUpRequest accountSignUpRequest,
        RedirectAttributes redirectAttributes) {
        ResultStatus signUpResult = accountService.signUp(accountSignUpRequest);

        if (signUpResult == ResultStatus.SUCCESS) {
            return RedirectMappings.REDIRECT_SIGN_IN;
        } else if (signUpResult == ResultStatus.DUPLICATE_USERNAME) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "이미 존재하는 계정입니다.");
            return RedirectMappings.REDIRECT_SIGN_UP;
        } else if (signUpResult == ResultStatus.DUPLICATE_NICKNAME) {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "이미 존재하는 닉네임입니다.");
            return RedirectMappings.REDIRECT_SIGN_UP;
        } else {
            redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "처리 오류");
            return RedirectMappings.REDIRECT_SIGN_UP;
        }
    }

    @PostMapping("/account/change/nickname")
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

    @PostMapping("/account/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return RedirectMappings.REDIRECT_INDEX;
    }

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
