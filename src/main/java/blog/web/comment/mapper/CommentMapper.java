package blog.web.comment.mapper;

import blog.domain.entity.Comment;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.request.CreateRepliesRequestDto;
import blog.web.comment.controller.dto.response.*;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CreateCommentRequestDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }

    public CreateCommentResponseDto toCreateDto(Comment comment) {
        return CreateCommentResponseDto.builder()
                .userId(comment.getUser().getUserId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate().toLocalDateTime())
                .build();
    }

    public UpdateCommentResponseDto toUpdateDto(Comment updateComment) {
        return UpdateCommentResponseDto.builder()
                .content(updateComment.getContent())
                .modifiedDate(updateComment.getModifiedDate())
                .build();
    }

    public DeleteCommentResponseDto toDeleteDto(Comment comment) {
        return DeleteCommentResponseDto.builder()
                .commentNo(comment.getCommentNo())
                .build();
    }

    public CommentListResponseDto toCommentListDto(Comment comment) {
        return CommentListResponseDto.builder()
                .commentNo(comment.getCommentNo())
                .userId(comment.getUser().getUserId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate().toLocalDateTime())
                .build();
    }

    public Comment toRepliesEntity(CreateRepliesRequestDto createRepliesRequestDto) {
        return Comment.builder()
                .content(createRepliesRequestDto.getContent())
                .build();
    }

    public CreateRepliesResponseDto toCreateRepliesDto(Comment afterComment) {
        return CreateRepliesResponseDto.builder()
                .userId(afterComment.getUser().getUserId())
                .content(afterComment.getContent())
                .createDate(afterComment.getCreatedDate().toLocalDateTime())
                .build();
    }
}
