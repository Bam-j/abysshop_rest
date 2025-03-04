package com.joo.abysshop.service.account;

import com.joo.abysshop.dto.account.AccountSignInRequest;
import com.joo.abysshop.dto.account.AccountSignUpRequest;
import com.joo.abysshop.dto.account.AccountWithdrawRequest;
import com.joo.abysshop.dto.account.ChangeNicknameRequest;
import com.joo.abysshop.dto.account.ChangePasswordRequest;
import com.joo.abysshop.entity.account.AccountEntity;
import com.joo.abysshop.entity.account.SignInEntity;
import com.joo.abysshop.entity.account.SignUpEntity;
import com.joo.abysshop.entity.user.UserEntity;
import com.joo.abysshop.util.enums.ResultStatus;
import com.joo.abysshop.mapper.dto.ToAccountDTOMapper;
import com.joo.abysshop.mapper.entity.ToAccountEntityMapper;
import com.joo.abysshop.mapper.mybatis.AccountMapper;
import com.joo.abysshop.mapper.mybatis.UserMapper;
import com.joo.abysshop.util.security.PasswordSecurity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final UserMapper userMapper;
    private final ToAccountDTOMapper toAccountDTOMapper;
    private final ToAccountEntityMapper toAccountEntityMapper;

    public ResultStatus signIn(AccountSignInRequest accountSignInRequest) {
        String username = accountSignInRequest.getUsername();
        Optional<AccountEntity> optionalAccountEntity = accountMapper.findByUsername(username);
        SignInEntity signInEntity;

        //잘못된 username
        if (optionalAccountEntity.isEmpty()) {
            return ResultStatus.INVALID_USERNAME;
        }

        AccountEntity accountEntity = optionalAccountEntity.get();
        signInEntity = SignInEntity.builder()
            .username(accountEntity.getUsername())
            .nickname(accountEntity.getNickname())
            .password(accountEntity.getPassword())
            .build();

        String inputPassword = accountSignInRequest.getPassword();
        String encodedPassword = signInEntity.getPassword();

        if (PasswordSecurity.matches(inputPassword, encodedPassword)) {
            return ResultStatus.SUCCESS;
        } else {
            return ResultStatus.INVALID_PASSWORD;
        }
    }

    public ResultStatus signUp(AccountSignUpRequest accountSignUpRequest) {
        String username = accountSignUpRequest.getUsername();
        Optional<AccountEntity> optionalAccountEntity = accountMapper.findByUsername(username);

        //username 조회 결과가 존재하면 회원가입 요청 실패
        if (optionalAccountEntity.isPresent()) {
            return ResultStatus.DUPLICATE_USERNAME;
        }

        String nickname = accountSignUpRequest.getNickname();
        optionalAccountEntity = accountMapper.findByNickname(nickname);

        //nickname 조회 결과가 존재하면 회원가입 요청 실패
        if (optionalAccountEntity.isPresent()) {
            return ResultStatus.DUPLICATE_NICKNAME;
        }

        String encryptedPassword = PasswordSecurity
            .encryptPassword(accountSignUpRequest.getPassword());

        SignUpEntity signUpEntity = toAccountEntityMapper
            .toSignUpEntity(accountSignUpRequest, encryptedPassword);
        accountMapper.insertUser(signUpEntity);

        return ResultStatus.SUCCESS;
    }

    public ResultStatus changeNickname(ChangeNicknameRequest changeNicknameRequest) {
        Long userId = changeNicknameRequest.getUserId();
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(userId);

        if (optionalUserEntity.isEmpty()) {
            //id 조회 결과 users_table에 유저가 없음 = 잘못된 요청
            return ResultStatus.BAD_REQUEST;
        }

        UserEntity userEntity = optionalUserEntity.get();
        String oldNickname = userEntity.getNickname();
        String newNickname = changeNicknameRequest.getNewNickname();

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

    public ResultStatus changePassword(ChangePasswordRequest changePasswordRequest) {
        Long userId = changePasswordRequest.getUserId();
        Optional<UserEntity> optionalUserEntity = userMapper.findByUserId(userId);

        if (optionalUserEntity.isEmpty()) {
            //id 조회 결과 users_table에 유저가 없음 = 잘못된 요청
            return ResultStatus.BAD_REQUEST;
        }

        UserEntity userEntity = optionalUserEntity.get();
        String oldPassword = userEntity.getPassword();
        String newPassword = changePasswordRequest.getNewPassword();

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
