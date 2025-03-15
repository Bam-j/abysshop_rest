package com.joo.abysshop.aspect;

import com.joo.abysshop.dto.user.response.UserInfoResponse;
import com.joo.abysshop.util.exception.UnauthorizedAccessException;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AdminCheckAspect {

    private final UserMapper userMapper;
    private final HttpSession session;

    @Before("@annotation(com.joo.abysshop.annotation.AdminOnly)")
    public void checkAdminAccess() {
        UserInfoResponse user = (UserInfoResponse) session.getAttribute("user");
        if (user == null || !userMapper.getUserType(user.getUserId()).equals("admin")) {
            throw new UnauthorizedAccessException("관리자 권한이 필요합니다.");
        }
    }
}