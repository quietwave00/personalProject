package blog.web.board.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardResponseDto {
    private Long boardNo;
    private String content;
    private String userId;
    private LocalDateTime createdDate;
    private List<String> tagList;
}
