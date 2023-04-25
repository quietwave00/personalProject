package blog.web.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreateRepliesResponseDto {
    private Long commentNo;
    private String userId;
    private String content;
    private LocalDateTime createDate;
}
