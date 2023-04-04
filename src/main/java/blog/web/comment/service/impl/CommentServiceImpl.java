package blog.web.comment.service.impl;

import blog.domain.entity.Board;
import blog.domain.entity.Comment;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.utils.dto.ApiError;
import blog.web.board.repository.BoardRepository;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;
import blog.web.comment.mapper.CommentMapper;
import blog.web.comment.repository.CommentRepository;
import blog.web.comment.service.CommentService;
import blog.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper mapper;
    private final UserRepository userRepository;

    @Override
    public CreateCommentResponseDto create(CreateCommentRequestDto createCommentRequestDto) {
        Board findBoard = boardRepository.findByBoardNo(createCommentRequestDto.getBoardNo())
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
        User findUser = userRepository.findByUserId(createCommentRequestDto.getUserId())
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));

        Comment beforeComment = mapper.toEntity(createCommentRequestDto);
        beforeComment.addBoard(findBoard);
        beforeComment.addUser(findUser);

        Comment afterComment = commentRepository.save(beforeComment);

        return mapper.toCreateDto(afterComment);
    }
}
