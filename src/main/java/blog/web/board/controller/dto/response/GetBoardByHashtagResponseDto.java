package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetBoardByHashtagResponseDto {
    private Long boardNo;
    private String content;
    private String nickname;
}
