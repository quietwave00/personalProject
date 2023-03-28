package project.blog.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreateBoardResponseDto {
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdDate;



}
