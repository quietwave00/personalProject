package project.blog.board.controller.dto.request;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {
    private Long boardNo;
    private String title;
    private String content;
    private String userId;
}
