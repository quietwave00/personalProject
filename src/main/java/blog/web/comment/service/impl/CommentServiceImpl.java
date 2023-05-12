package blog.web.comment.service.impl;

import blog.domain.entity.Board;
import blog.domain.entity.Comment;
import blog.domain.entity.Status;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.jwt.UserContextHolder;
import blog.utils.dto.ApiError;
//import blog.web.alert.repository.AlertRepository;
import blog.web.board.repository.BoardRepository;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.request.CreateRepliesRequestDto;
import blog.web.comment.controller.dto.request.DeleteCommentRequestDto;
import blog.web.comment.controller.dto.request.UpdateCommentRequestDto;
import blog.web.comment.controller.dto.response.*;
import blog.web.comment.mapper.CommentMapper;
import blog.web.comment.repository.CommentRepository;
import blog.web.comment.service.CommentService;
import blog.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
//    private final AlertRepository alertRepository;
    private final CommentMapper mapper;

    @Override
    public CreateCommentResponseDto create(CreateCommentRequestDto createCommentRequestDto) {
        Board findBoard = findBoard(createCommentRequestDto.getBoardNo());
        User findUser = findUser(UserContextHolder.getUserId());

        Comment beforeComment = mapper.toEntity(createCommentRequestDto);
        beforeComment.addBoard(findBoard);
        beforeComment.addUser(findUser);

        Comment afterComment = commentRepository.save(beforeComment);

        return mapper.toCreateDto(afterComment);
    }

    @Override
    public UpdateCommentResponseDto update(UpdateCommentRequestDto updateCommentRequestDto) {
        Comment findComment = findComment(updateCommentRequestDto.getCommentNo());
        findComment.update(updateCommentRequestDto.getContent());
        Comment updateComment = commentRepository.save(findComment);
        return mapper.toUpdateDto(updateComment);
    }

    @Override
    public DeleteCommentResponseDto delete(DeleteCommentRequestDto deleteCommentRequestDto) {
        Comment findComment = findComment(deleteCommentRequestDto.getCommentNo());
        findComment.delete();
        commentRepository.save(findComment);
        return mapper.toDeleteDto(findComment);
    }

    @Override
    public List<CommentListResponseDto> select(Long boardNo) {
        Board board = findBoard(boardNo);
        List<Comment> commentList = commentRepository.findByBoardAndStatusAndParentIsNull(board, Status.Y);

        List<CommentListResponseDto> commentListResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            List<CommentListResponseDto.Replies> replies = comment.getChildren().stream()
                    .map(mapper::toReplies)
                    .collect(Collectors.toList());
            CommentListResponseDto commentListResponseDto = mapper.toCommentListDto(comment, replies);
            commentListResponseDtoList.add(commentListResponseDto);
        }
        return commentListResponseDtoList;
    }

    @Override
    public CreateRepliesResponseDto createReplies(CreateRepliesRequestDto createRepliesRequestDto) {
        Board findBoard = findBoard(createRepliesRequestDto.getBoardNo());
        User findUser = findUser(UserContextHolder.getUserId());
        Comment findParent = findComment(createRepliesRequestDto.getParentNo());

        Comment beforeComment = mapper.toRepliesEntity(createRepliesRequestDto);
        beforeComment.addBoard(findBoard);
        beforeComment.addUser(findUser);
        beforeComment.addParent(findParent);

        Comment afterComment = commentRepository.save(beforeComment);

        return mapper.toCreateRepliesDto(afterComment);
    }


    //단일 메소드
    Board findBoard(Long boardNo) {
        return boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
    }

    User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
    }

    Comment findComment(Long commentNo) {
        return commentRepository.findByCommentNo(commentNo)
                .orElseThrow(() -> new ApiError(ErrorCode.COMMENT_NOT_FOUND));
    }


}
