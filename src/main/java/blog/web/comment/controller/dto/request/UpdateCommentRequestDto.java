package blog.web.comment.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCommentRequestDto {
    private Long commentNo;
    private String content;
    private String userId;

}
