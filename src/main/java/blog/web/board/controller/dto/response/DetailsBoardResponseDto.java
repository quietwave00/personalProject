package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class DetailsBoardResponseDto {
    String title;
    String content;
    String userId;
    LocalDateTime createdDate;
    Integer count;

}
