package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardListResponseDto {
    private Long boardNo;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String nickname;

}
