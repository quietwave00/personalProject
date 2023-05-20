package blog.web.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikeBoardResponseDto {
    private String userId;
}
