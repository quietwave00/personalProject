package project.blog.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.request.DeleteBoardRequestDto;
import project.blog.board.controller.dto.request.DetailsBoardRequestDto;
import project.blog.board.controller.dto.request.UpdateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;
import project.blog.board.controller.dto.response.DeleteBoardResponseDto;
import project.blog.board.controller.dto.response.DetailsBoardResponseDto;
import project.blog.board.controller.dto.response.UpdateBoardResponseDto;
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
    @PostMapping("/boards")
    public ApiResult<CreateBoardResponseDto> create(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        CreateBoardResponseDto createBoardResponseDto = boardService.create(createBoardRequestDto);
        return ApiUtils.success(createBoardResponseDto);
    }

    //글 수정
    @PatchMapping("/boards")
    public ApiResult<UpdateBoardResponseDto> update(@RequestBody UpdateBoardRequestDto updateBoardRequestDto) {
        UpdateBoardResponseDto updateBoardResponseDto = boardService.update(updateBoardRequestDto);
        return ApiUtils.success(updateBoardResponseDto);
    }

    //글 상세 조회
    @GetMapping("/boards")
    public ApiResult<DetailsBoardResponseDto> detail(@RequestBody DetailsBoardRequestDto detailsBoardRequestDto) {
        DetailsBoardResponseDto updateBoardResponseDto = boardService.detail(detailsBoardRequestDto);
        return ApiUtils.success(updateBoardResponseDto);
    }

    //글 삭제
    @PatchMapping("/boards/status")
    public ApiResult<DeleteBoardResponseDto> delete(@RequestBody DeleteBoardRequestDto deleteBoardRequestDto) {
        DeleteBoardResponseDto deleteBoardResponseDto = boardService.delete(deleteBoardRequestDto);
        return ApiUtils.success(deleteBoardResponseDto);
    }

}
