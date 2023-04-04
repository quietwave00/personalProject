package blog.web.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UpdateCommentResponseDto {
    String content;
    LocalDateTime modifiedDate;
}
