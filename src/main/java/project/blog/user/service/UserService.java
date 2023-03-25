package project.blog.user.service;

import project.blog.user.dto.request.JoinRequestDto;
import project.blog.user.dto.request.LoginRequestDto;
import project.blog.user.dto.response.JoinResponseDto;
import project.blog.user.dto.response.LoginResponseDto;
import project.blog.utils.dto.ApiError;

public interface UserService {

    JoinResponseDto join(JoinRequestDto joinRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto) throws ApiError;
}
