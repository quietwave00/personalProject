package project.blog.user.service.impl;

import ch.qos.logback.core.sift.AbstractAppenderFactoryUsingJoran;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.blog.user.dto.request.JoinRequestDto;
import project.blog.user.dto.request.LoginRequestDto;
import project.blog.user.dto.response.JoinResponseDto;
import project.blog.user.dto.response.LoginResponseDto;
import project.blog.domain.entity.User;
import project.blog.user.exception.ErrorCode;
import project.blog.user.repository.UserRepository;
import project.blog.user.service.UserService;
import project.blog.utils.ApiUtils;
import project.blog.utils.dto.ApiError;
import java.util.function.Supplier;

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
