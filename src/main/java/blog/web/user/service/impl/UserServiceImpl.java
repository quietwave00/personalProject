package blog.web.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import blog.web.user.dto.request.JoinRequestDto;
import blog.web.user.dto.request.LoginRequestDto;
import blog.web.user.dto.response.JoinResponseDto;
import blog.web.user.dto.response.LoginResponseDto;
import blog.domain.entity.User;
import blog.web.user.exception.ErrorCode;
import blog.web.user.repository.UserRepository;
import blog.web.user.service.UserService;
import blog.utils.dto.ApiError;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    //가입
    @Override
    public JoinResponseDto join(JoinRequestDto joinRequestDto) {
        String encodedPwd = encoder.encode(joinRequestDto.getPassword());
        joinRequestDto.setPassword(encodedPwd);
        User user = User.createUser(joinRequestDto);

        return User.toJoinDto(userRepository.save(user));
    }

    //로그인
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        //아이디 검증
        User findUser = userRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));

        //비밀번호 검증
        if(!encoder.matches(loginRequestDto.getPassword(), findUser.getPassword())) {
            throw new ApiError(ErrorCode.INVALID_PASSWORD);
        }

        return User.toLoginDto(findUser);
    }


}
