package blog.web.comment.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteCommentRequestDto {
    private Long commentNo;
    private String userId;
}
