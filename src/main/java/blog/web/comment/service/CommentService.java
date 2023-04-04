package blog.web.comment.service;

import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;

public interface CommentService {
    CreateCommentResponseDto create(CreateCommentRequestDto createCommentRequestDto);
}
