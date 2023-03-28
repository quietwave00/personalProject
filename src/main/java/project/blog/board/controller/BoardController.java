package project.blog.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;
import project.blog.board.service.BoardService;
import project.blog.utils.ApiUtils;
import project.blog.utils.dto.ApiResult;

@Slf4j
@RestController
@RequestMapping("/blog/user")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    //글 작성
    @PostMapping("/board")
    public ApiResult<CreateBoardResponseDto> create(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        CreateBoardResponseDto createBoardResponseDto = boardService.create(createBoardRequestDto);
        return ApiUtils.success(createBoardResponseDto);
    }
}
