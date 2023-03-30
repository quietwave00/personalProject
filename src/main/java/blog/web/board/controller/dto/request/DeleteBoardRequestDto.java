package blog.web.board.controller.dto.request;

import lombok.Getter;

@Getter
public class DeleteBoardRequestDto {
    private Long boardNo;
    private String userId;
}
