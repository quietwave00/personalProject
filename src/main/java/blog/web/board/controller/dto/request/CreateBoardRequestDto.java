package blog.web.board.controller.dto.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBoardRequestDto {
    private String content;
    private List<String> hashtagList;
    private String trackId;
}
