package project.blog.user.contorller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.blog.user.dto.request.JoinRequestDto;
import project.blog.user.dto.request.LoginRequestDto;
import project.blog.user.dto.response.JoinResponseDto;
import project.blog.user.dto.response.LoginResponseDto;
import project.blog.user.service.impl.UserServiceImpl;
import project.blog.utils.ApiUtils;
import project.blog.utils.dto.ApiResult;

@RequestMapping("/blog")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/home")
    public @ResponseBody String home() {
        return "<h1>home</h1>";
    }

    //회원가입
    @PostMapping("/join")
    public ApiResult<JoinResponseDto> join(@RequestBody JoinRequestDto joinRequestDto) {
        JoinResponseDto joinResponseDto = userService.join(joinRequestDto);
        return ApiUtils.success(joinResponseDto);
    }







}
