package blog.web.file.controller.dto.response;

import blog.domain.entity.FileLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadFileResponseDto {
    private Long fileNo;
    private String filePath;
    private FileLevel fileLevel;
}
