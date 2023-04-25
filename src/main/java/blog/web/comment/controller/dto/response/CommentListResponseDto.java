package blog.web.comment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommentListResponseDto {
    private Long commentNo;
    private String userId;
    private String content;
    private LocalDateTime createdDate;
    private List<Replies> repliesList;

    @Builder
    @Getter
    public static class Replies {
        private String userId;
        private String content;
        private LocalDateTime createdDate;
    }
}
