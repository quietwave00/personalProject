package blog.web.board.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardResponseDto {
    private String content;
    private String userId;
    private LocalDateTime modifiedDate;

}
