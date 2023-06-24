package blog.web.board.service;

import blog.domain.entity.Board;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;
import blog.web.board.controller.dto.response.GetBoardByHashtagResponseDto;

import java.util.List;

public interface BoardService {
    CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto);

    UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto);

    DetailsBoardResponseDto detail(Long boardNo);

    DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto);

    Result<?> selectAll();

    List<BoardOfTrackResponseDto> selectBoardOfTrack(String trackId, String order);

    String likeBoard(Long boardNo);

    String unLikeBoard(Long boardNo);

    Long countLikes(Long boardNo);

    Boolean isBoardLiked(Long boardNo);

    List<GetBoardByHashtagResponseDto> getBoardByHashtag(String hashtag);

    Board findBoard(Long boardNo);
}
