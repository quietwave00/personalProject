package blog.web.board.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsBoardResponseDto {
    String content;
    String userId;
    LocalDateTime createdDate;
    Integer count;
    List<String> hashtagList = new ArrayList<>();

}
