package blog.web.mypage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentListOfMyPageResponseDto {
    private Long boardNo;
    private Long commentNo;
    private String content;
    private LocalDateTime createdDate;
}
