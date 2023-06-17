package blog.web.file.mapper;

import blog.domain.entity.File;
import blog.web.file.controller.dto.response.UploadResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public UploadResponseDto toUploadDto(File file) {
        return UploadResponseDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .fileLevel(file.getFileLevel())
                .build();
    }
}
