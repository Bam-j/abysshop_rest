package com.joo.abysshop.aspect;

import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.util.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserCheckAspect {

    private final HttpSession session;

    @Before("@annotation(com.joo.abysshop.annotation.CurrentUserOnly)")
    public void checkCurrentUserAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Long requestUserId = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Long) {
                requestUserId = (Long) args[i];
                break;
            }
        }

        Long currentUserId = ((UserInfoResponse) session.getAttribute("user")).getUserId();

        if (!currentUserId.equals(requestUserId)) {
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
    }
}
