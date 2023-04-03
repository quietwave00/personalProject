package blog.web.board.service;

import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;

public interface BoardService {
    CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto);

    UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto);

    DetailsBoardResponseDto detail(Long boardNo, String userId);

    DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto);

    Result<?> selectAll();
}
