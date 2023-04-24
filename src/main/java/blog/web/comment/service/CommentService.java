package blog.web.comment.service;

import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.request.CreateRepliesRequestDto;
import blog.web.comment.controller.dto.request.DeleteCommentRequestDto;
import blog.web.comment.controller.dto.request.UpdateCommentRequestDto;
import blog.web.comment.controller.dto.response.*;

import java.util.List;

public interface CommentService {
    CreateCommentResponseDto create(CreateCommentRequestDto createCommentRequestDto);

    UpdateCommentResponseDto update(UpdateCommentRequestDto updateCommentRequestDto);

    DeleteCommentResponseDto delete(DeleteCommentRequestDto deleteCommentRequestDto);

    List<CommentListResponseDto> select(Long boardNo);

    CreateRepliesResponseDto createReplies(CreateRepliesRequestDto createRepliesRequestDto);
}
