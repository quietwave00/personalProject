package blog.web.board.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardRequestDto {
    private Long boardNo;
    private String userId;

}
