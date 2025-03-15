package com.joo.abysshop.aspect;

import com.joo.abysshop.util.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserCheckAspect {

    //TODO: 권한 검증 Aspect들 JWT 인증에 맞게 변경하기
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

        //JWT 토큰 인증에서 userId를 가져온다.

        if (!currentUserId.equals(requestUserId)) {
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
    }
}
