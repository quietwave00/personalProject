package blog.web.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;
import blog.web.board.service.BoardService;
import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;

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
    @GetMapping("/boards/{boardNo}/{userId}")
    public ApiResult<DetailsBoardResponseDto> detail(@PathVariable("boardNo") Long boardNo, @PathVariable("userId") String userId) {
        DetailsBoardResponseDto updateBoardResponseDto = boardService.detail(boardNo, userId);
        return ApiUtils.success(updateBoardResponseDto);
    }

    //글 삭제
    @PatchMapping("/boards/status")
    public ApiResult<DeleteBoardResponseDto> delete(@RequestBody DeleteBoardRequestDto deleteBoardRequestDto) {
        DeleteBoardResponseDto deleteBoardResponseDto = boardService.delete(deleteBoardRequestDto);
        return ApiUtils.success(deleteBoardResponseDto);
    }

    //글 전체 조회
    @GetMapping("/boards")
    public ApiResult<SelectBoardResponseDto> selectAll() {
        SelectBoardResponseDto selectBoardResponseDto = boardService.selectAll();
        return ApiUtils.success(selectBoardResponseDto);
    }

}
