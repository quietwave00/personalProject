package project.blog.board.service;

import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;

public interface BoardService {
    CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto);
}
