package blog.web.file.controller.dto.response;

import blog.domain.entity.FileLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadResponseDto {
    private Long fileNo;
    private String filePath;
    private FileLevel fileLevel;
}
