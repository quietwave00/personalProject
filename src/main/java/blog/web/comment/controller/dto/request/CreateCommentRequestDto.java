package blog.web.comment.controller.dto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    private Long boardNo;
    private String content;
}
