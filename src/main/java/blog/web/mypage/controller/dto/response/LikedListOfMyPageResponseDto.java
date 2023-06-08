package blog.web.mypage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikedListOfMyPageResponseDto {
    private Long boardNo;
    private String content;
    private String nickname;
}
