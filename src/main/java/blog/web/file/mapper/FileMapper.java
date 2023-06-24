package blog.web.file.mapper;

import blog.domain.entity.Board;
import blog.domain.entity.File;
import blog.domain.entity.FileLevel;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.service.dto.SaveFileDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {

    public UploadFileResponseDto toUploadDto(File file) {
        return UploadFileResponseDto.builder()
                .fileNo(file.getFileNo())
                .filePath(file.getFilePath())
                .fileLevel(file.getFileLevel())
                .build();
    }


    public List<UploadFileResponseDto> toUploadDtoList(List<File> files) {
        return files.stream()
                .map(file -> UploadFileResponseDto.builder()
                        .fileNo(file.getFileNo())
                        .filePath(file.getFilePath())
                        .fileLevel(file.getFileLevel())
                        .build())
                .collect(Collectors.toList());
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

    public List<File> toEntities(List<SaveFileDto> saveFileDtoList, Board board) {
        return saveFileDtoList.stream()
                .map(saveFileDto -> {
                    FileLevel fileLevel = (saveFileDto.getIndex() == 0) ? FileLevel.MAIN : FileLevel.SUB;
                    return File.builder()
                            .fileName(saveFileDto.getOriginalName())
                            .fileLevel(fileLevel)
                            .filePath(saveFileDto.getFilePath())
                            .board(board)
                            .build();
                })
                .collect(Collectors.toList());
    }


}
