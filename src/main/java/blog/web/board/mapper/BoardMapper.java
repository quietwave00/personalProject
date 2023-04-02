package blog.web.board.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.controller.dto.response.CreateBoardResponseDto;
import blog.web.board.controller.dto.response.DeleteBoardResponseDto;
import blog.web.board.controller.dto.response.DetailsBoardResponseDto;
import blog.web.board.controller.dto.response.UpdateBoardResponseDto;
import blog.domain.entity.Board;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@ComponentScan("blog.config")
@RequiredArgsConstructor
public class BoardMapper {

    private final ModelMapper modelMapper;

    public Board toEntity(CreateBoardRequestDto createBoardRequestDto) {
        return modelMapper.map(createBoardRequestDto, Board.class);
    }

    public CreateBoardResponseDto toCreateDto(Board board) {
        return modelMapper.map(board, CreateBoardResponseDto.class);
    }

    public UpdateBoardResponseDto toUpdateDto(Board board) {
        return modelMapper.map(board, UpdateBoardResponseDto.class);
    }

    public DetailsBoardResponseDto toDetailsDto(Board board) {
        return modelMapper.map(board, DetailsBoardResponseDto.class);
    }

    public DeleteBoardResponseDto toDeleteDto(Board board) {
        return modelMapper.map(board, DeleteBoardResponseDto.class);
    }







}
