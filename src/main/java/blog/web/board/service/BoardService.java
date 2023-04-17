package blog.web.board.service;

import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;

import java.util.List;

public interface BoardService {
    CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto);

    UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto);

    DetailsBoardResponseDto detail(Long boardNo);

    DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto);

    Result<?> selectAll();

    List<BoardOfTrackResponseDto> selectBoardOfTrack(String trackId);

    String likeBoard(Long boardNo);

    String unLikeBoard(Long boardNo);

    Long countLikes(Long boardNo);

    Boolean isBoardLiked(Long boardNo);
}
