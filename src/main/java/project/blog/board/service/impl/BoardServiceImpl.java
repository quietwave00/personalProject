package project.blog.board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        log.info("여기까지 옴?");
        User findUser = userRepository.findByUserId(createBoardRequestDto.getUserId())
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
        Board board = boardRepository.save(createBoardRequestDto.toEntity().addUser(findUser));

        return Board.toCreateDto(board);
    }
}
