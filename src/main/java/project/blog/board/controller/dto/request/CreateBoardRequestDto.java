package project.blog.board.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import project.blog.domain.entity.Board;
import project.blog.domain.entity.User;

@Builder
@Getter
public class CreateBoardRequestDto {
    private String title;
    private String content;
    private String userId;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }



}
