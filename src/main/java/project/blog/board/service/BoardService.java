package project.blog.board.service;

import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.request.DeleteBoardRequestDto;
import project.blog.board.controller.dto.request.DetailsBoardRequestDto;
import project.blog.board.controller.dto.request.UpdateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;
import project.blog.board.controller.dto.response.DeleteBoardResponseDto;
import project.blog.board.controller.dto.response.DetailsBoardResponseDto;
import project.blog.board.controller.dto.response.UpdateBoardResponseDto;

public interface BoardService {
    CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto);

    UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto);

    DetailsBoardResponseDto detail(DetailsBoardRequestDto detailsBoardRequestDto);

    DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto);
}
