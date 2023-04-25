package blog.web.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateCommentResponseDto {
    private Long commentNo;
    private String userId;
    private String content;
    private LocalDateTime createdDate;

}
