package blog.web.file.service;


import blog.web.file.controller.dto.response.UploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<UploadResponseDto> upload(List<MultipartFile> multipartFileList, Long boardNo) throws IOException;
}
