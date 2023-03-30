package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class UpdateBoardResponseDto {
    private String title;
    private String content;
    private String userId;
    private LocalDateTime modifiedDate;

}
