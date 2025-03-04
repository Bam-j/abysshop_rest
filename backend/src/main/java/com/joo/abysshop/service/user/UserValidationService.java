package com.joo.abysshop.service.user;

import com.joo.abysshop.dto.user.UserInfoResponse;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserMapper userMapper;

    public Boolean isAdmin(final Long userId) {
        //관리자 관련 요청시 현재 로그인 중인 계정의 userType이 'admin'인지 검증
        String userType = userMapper.getUserType(userId);
        return userType.equals("admin");
    }

    public Boolean isCurrentUser(final Long userId, final HttpSession session) {
        //장바구니 or mypage 요청시 세션에 담긴 현재 로그인 사용자 userId와 요청하는 userId가 일치하는지 검증
        Long currentUserId = ((UserInfoResponse) session.getAttribute("user")).getUserId();
        return  currentUserId.equals(userId);
    }
}
