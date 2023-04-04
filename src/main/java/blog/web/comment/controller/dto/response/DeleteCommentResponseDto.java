package blog.web.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteCommentResponseDto {
    private Long commentNo;
}
