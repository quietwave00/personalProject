package project.blog.board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.blog.board.controller.dto.request.DeleteBoardRequestDto;
import project.blog.board.controller.dto.request.DetailsBoardRequestDto;
import project.blog.board.controller.dto.request.UpdateBoardRequestDto;
import project.blog.board.controller.dto.response.DeleteBoardResponseDto;
import project.blog.board.controller.dto.response.DetailsBoardResponseDto;
import project.blog.board.controller.dto.response.UpdateBoardResponseDto;
import project.blog.domain.entity.Board;
import project.blog.domain.entity.User;
import project.blog.user.exception.ErrorCode;
import project.blog.user.repository.UserRepository;
import project.blog.utils.dto.ApiError;
import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;
import project.blog.board.repository.BoardRepository;
import project.blog.board.service.BoardService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto) {
        User findUser = userRepository.findByUserId(createBoardRequestDto.getUserId())
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
        Board board = boardRepository.save(createBoardRequestDto.toEntity().addUser(findUser));

        return Board.toCreateDto(board);
    }

    @Override
    public UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(updateBoardRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        findBoard.update(updateBoardRequestDto.getTitle(), updateBoardRequestDto.getContent());
        Board updateBoard = boardRepository.save(findBoard);
        return Board.toUpdateDto(updateBoard);
    }

    @Override
    public DetailsBoardResponseDto detail(DetailsBoardRequestDto detailsBoardRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(detailsBoardRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        //조회수
        if(!findBoard.getUser().getUserId().equals(detailsBoardRequestDto.getUserId())) {
            findBoard.updateCount();
            boardRepository.save(findBoard);
        }
        return Board.toDetailDto(findBoard);
    }

    @Override
    public DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(deleteBoardRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        findBoard.deleteBoard(findBoard);
        boardRepository.save(findBoard);
        return Board.toDeleteDto(findBoard);
    }
}
