package blog.web.board.service.impl;

import blog.domain.entity.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;
import blog.web.board.mapper.BoardMapper;
import blog.domain.entity.Board;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.web.user.repository.UserRepository;
import blog.utils.dto.ApiError;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.repository.BoardRepository;
import blog.web.board.service.BoardService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardMapper mapper;



    @Override
    public CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto) {
        User findUser = userRepository.findByUserId(createBoardRequestDto.getUserId())
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
        Board board = boardRepository.save(mapper.toEntity(createBoardRequestDto).addUser(findUser));
        return mapper.toCreateDto(board);
    }

    @Override
    public UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(updateBoardRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        findBoard.update(updateBoardRequestDto.getTitle(), updateBoardRequestDto.getContent());
        Board updateBoard = boardRepository.save(findBoard);
        return mapper.toUpdateDto(updateBoard);
    }

    @Override
    public DetailsBoardResponseDto detail(Long boardNo, String userId) {
        Board findBoard = boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        //조회수
        if(!findBoard.getUser().getUserId().equals(userId)) {
            findBoard.updateCount();
            boardRepository.save(findBoard);
        }
        return mapper.toDetailsDto(findBoard);
    }

    @Override
    public DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(deleteBoardRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        findBoard.deleteBoard(findBoard);
        boardRepository.save(findBoard);
        return mapper.toDeleteDto(findBoard);
    }

    @Override
    public Result<?> selectAll() {
        List<Board> boardList = boardRepository.findByStatusOrderByBoardNoDesc(Status.Y);
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardListResponseDto boardListResponseDto = mapper.toBoardListDto(board);
            boardListResponseDtoList.add(boardListResponseDto);
        }
        return new Result<>(boardListResponseDtoList);
    }
}
