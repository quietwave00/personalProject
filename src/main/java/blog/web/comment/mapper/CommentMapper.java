package blog.web.comment.mapper;

import blog.domain.entity.Comment;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;
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
}
