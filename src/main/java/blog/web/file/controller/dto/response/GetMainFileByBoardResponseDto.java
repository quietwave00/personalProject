package blog.web.file.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMainFileByBoardResponseDto {
    private Long fileNo;
    private String filePath;
}
