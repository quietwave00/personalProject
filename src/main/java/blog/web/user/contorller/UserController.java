package blog.web.user.contorller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import blog.web.user.dto.request.JoinRequestDto;
import blog.web.user.dto.response.JoinResponseDto;
import blog.web.user.service.impl.UserServiceImpl;
import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;

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
