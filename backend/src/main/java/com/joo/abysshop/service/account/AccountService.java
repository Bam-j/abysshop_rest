package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.AccountSignInRequest;
import com.joo.abysshop.dto.account.AccountSignUpRequest;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.dto.account.UpdateNicknameRequest;
import com.joo.abysshop.dto.account.UpdatePasswordRequest;
import com.joo.abysshop.repository.user.UserRepository;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.util.security.PasswordSecurity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    public ResultStatus changeNickname(UpdateNicknameRequest updateNicknameRequest) {
        /*
         *  1. users_table에서 현재 저장된 nickname과 전달된 newNickname이 일치하는지 확인 > 실패: SAME_NICKNAME
         *  2. 이상 없는 경우 userId user update nickname
         */
        Long userId = updateNicknameRequest.getUserId();
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(userId);

        if (optionalUserEntity.isEmpty()) {
            //id 조회 결과 users_table에 유저가 없음 = 잘못된 요청
            return ResultStatus.BAD_REQUEST;
        }

        UserEntity userEntity = optionalUserEntity.get();
        String oldNickname = userEntity.getNickname();
        String newNickname = updateNicknameRequest.getNewNickname();

        if (oldNickname.equals(newNickname)) {
            //바꾸려는 nickname과 현재 사용 중인 nickname이 동일함
            return ResultStatus.SAME_NICKNAME;
        } else if (newNickname == null || newNickname.trim().isEmpty()) {
            //form에 공백, 빈 입력이 들어옴
            return ResultStatus.EMPTY_INPUT_FORM;
        }

        Optional<AccountEntity> optionalAccountEntity = accountMapper.findByNickname(newNickname);

        if (optionalAccountEntity.isPresent()) {
            //바꾸려는 nickname을 가진 유저가 users_table에 존재함
            return ResultStatus.DUPLICATE_NICKNAME;
        }

        accountMapper.updateNickname(userId, newNickname);
        return ResultStatus.SUCCESS;
    }

    public ResultStatus changePassword(UpdatePasswordRequest updatePasswordRequest) {
        /*
         *  1. userId 통해 현재 비밀번호와 newPassword가 일치하는지 확인  > 실패: SAME_PASSWORD
         *  2. 이상 없는 경우 update password
         *  !update 비밀번호도 암호화하여 저장
         */
        Long userId = updatePasswordRequest.getUserId();
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(userId);

        if (optionalUserEntity.isEmpty()) {
            //id 조회 결과 users_table에 유저가 없음 = 잘못된 요청
            return ResultStatus.BAD_REQUEST;
        }

        UserEntity userEntity = optionalUserEntity.get();
        String oldPassword = userEntity.getPassword();
        String newPassword = updatePasswordRequest.getNewPassword();

        if (oldPassword.equals(newPassword)) {
            //바꾸려는 password과 현재 사용 중인 password이 동일함
            return ResultStatus.SAME_PASSWORD;
        } else if (newPassword == null || newPassword.trim().isEmpty()) {
            //form에 공백, 빈 입력이 들어옴
            return ResultStatus.EMPTY_INPUT_FORM;
        }

        accountMapper.updatePassword(userId, newPassword);
        return ResultStatus.SUCCESS;
    }

    public ResultStatus withdraw(AccountWithdrawRequest accountWithdrawRequest) {
        /*
         *  1. userId로 저장된 password 가져와서 입력받은 password와 비교 > 불일치: INVALID_PASSWORD
         *  2. 일치할 시 users_table에서 delete
         *  3. JWT 인증 해제
         */
        Long userId = accountWithdrawRequest.getUserId();
        String inputPassword = accountWithdrawRequest.getPassword();
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(userId);

        if (optionalUserEntity.isEmpty()) {
            //조회된 유저 없음 = 잘못된 요청
            return ResultStatus.BAD_REQUEST;
        }

        String encodedPassword = optionalUserEntity.get().getPassword();

        if (!PasswordSecurity.matches(inputPassword, encodedPassword)) {
            //유저의 password와 입력된 password가 불일치함
            return ResultStatus.INVALID_PASSWORD;
        }

        accountMapper.deleteUser(userId);
        return ResultStatus.SUCCESS;
    }
}
