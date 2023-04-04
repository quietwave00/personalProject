package blog.web.comment.service;

import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.request.DeleteCommentRequestDto;
import blog.web.comment.controller.dto.request.UpdateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;
import blog.web.comment.controller.dto.response.DeleteCommentResponseDto;
import blog.web.comment.controller.dto.response.UpdateCommentResponseDto;

public interface CommentService {
    CreateCommentResponseDto create(CreateCommentRequestDto createCommentRequestDto);

    UpdateCommentResponseDto update(UpdateCommentRequestDto updateCommentRequestDto);

    DeleteCommentResponseDto delete(DeleteCommentRequestDto deleteCommentRequestDto);
}
