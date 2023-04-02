package blog.web.board.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsBoardResponseDto {
    String title;
    String content;
    String userId;
    LocalDateTime createdDate;
    Integer count;

}
