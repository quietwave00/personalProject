package blog.web.board.controller.dto.request;

import lombok.*;
import blog.domain.entity.Board;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
