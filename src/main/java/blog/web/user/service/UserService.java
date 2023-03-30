package blog.web.user.service;

import blog.web.user.dto.request.JoinRequestDto;
import blog.web.user.dto.request.LoginRequestDto;
import blog.web.user.dto.response.JoinResponseDto;
import blog.web.user.dto.response.LoginResponseDto;
import blog.utils.dto.ApiError;

public interface UserService {

    JoinResponseDto join(JoinRequestDto joinRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto) throws ApiError;
}
