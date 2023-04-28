package blog.web.mypage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class BoardListOfMyPageResponseDto {
    private Long boardNo;
    private String content;
    private List<String> hashtagList;
    private String title;
    private LocalDateTime createdDate;

}
