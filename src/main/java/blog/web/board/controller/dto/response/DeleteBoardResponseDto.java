package blog.web.board.controller.dto.response;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardResponseDto {
    private Long boardNo;
}
