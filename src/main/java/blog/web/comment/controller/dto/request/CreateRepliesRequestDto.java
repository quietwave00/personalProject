package blog.web.comment.controller.dto.request;

import lombok.Getter;

@Getter
public class CreateRepliesRequestDto {
    private Long boardNo;
    private Long parentNo;
    private String content;
}
