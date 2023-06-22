package blog.web.file.mapper;

import blog.domain.entity.File;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
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

    public UpdateFileResponseDto toUpdateDto(File file) {
        return UpdateFileResponseDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .fileLevel(file.getFileLevel())
                .build();
    }

    public GetMainFileByBoardResponseDto toGetMainDto(File file) {
        return GetMainFileByBoardResponseDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .build();
    }
}
