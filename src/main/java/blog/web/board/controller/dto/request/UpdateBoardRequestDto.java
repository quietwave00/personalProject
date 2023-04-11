package blog.web.board.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardRequestDto {
    private Long boardNo;
    private String content;
    private String userId;
}
