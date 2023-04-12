package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BoardOfTrackResponseDto {
    private Long boardNo;
    private List<String> tagList;
    private String content;
}
