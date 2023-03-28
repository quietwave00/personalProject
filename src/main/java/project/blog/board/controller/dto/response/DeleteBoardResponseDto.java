package project.blog.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteBoardResponseDto {
    private Long boardNo;
}
