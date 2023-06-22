package blog.web.file.service;


import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<UploadFileResponseDto> upload(List<MultipartFile> multipartFileList, Long boardNo) throws IOException;

    List<UpdateFileResponseDto> update(UpdateFileRequestDto updateFileRequestDto) throws IOException;

    List<GetMainFileByBoardResponseDto> get(Long boardNo);
}
