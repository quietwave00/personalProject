package blog.web.file.mapper;

import blog.domain.entity.File;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public UploadFileResponseDto toUploadDto(File file) {
        return UploadFileResponseDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .fileLevel(file.getFileLevel())
                .build();
    }
}
