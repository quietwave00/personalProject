package blog.web.board.controller;

import blog.web.board.controller.dto.request.LikeBoardRequestDto;
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

import java.util.List;

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
    @GetMapping("/board/{boardNo}")
    public ApiResult<DetailsBoardResponseDto> detail(@PathVariable("boardNo") Long boardNo) {
        DetailsBoardResponseDto updateBoardResponseDto = boardService.detail(boardNo);
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
    public ApiResult<Result<?>> selectAll() {
        Result<?> selectBoardResponseDto = boardService.selectAll();
        return ApiUtils.success(selectBoardResponseDto);
    }

    //트랙별 글 조회(최신 순)
    @GetMapping("/boards/{trackId}")
    public ApiResult<List<BoardOfTrackResponseDto>> selectBoardOfTrack(@PathVariable("trackId") String trackId) {
        List<BoardOfTrackResponseDto> boardOfTrackResponseDtoList = boardService.selectBoardOfTrack(trackId);
        return ApiUtils.success(boardOfTrackResponseDtoList);
    }

    //좋아요 등록
    @PostMapping("/boards/like")
    public ApiResult<String> likeBoard(@RequestBody LikeBoardRequestDto likeBoardRequestDto) {
        String likeAlert = boardService.likeBoard(likeBoardRequestDto.getBoardNo());
        return ApiUtils.success(likeAlert);
    }

    //좋아요 취소
    @DeleteMapping("/boards/like/{boardNo}")
    public ApiResult<String> unLikeBoard(@PathVariable("boardNo") Long boardNo) {
        String likeAlert = boardService.unLikeBoard(boardNo);
        return ApiUtils.success(likeAlert);
    }

    //좋아요 개수 조회
    @GetMapping("/boards/like/{boardNo}")
    public ApiResult<Long> countLikes(@PathVariable("boardNo") Long boardNo) {
        Long countLikes = boardService.countLikes(boardNo);
        return ApiUtils.success(countLikes);
    }

    //좋아요 여부 조회
    @GetMapping("/boards/like/count/{boardNo}")
    public ApiResult<Boolean> isBoardLiked(@PathVariable("boardNo") Long boardNo) {
        Boolean checkLiked = boardService.isBoardLiked(boardNo);
        return ApiUtils.success(checkLiked);
    }



}
