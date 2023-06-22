package blog.web.file.controller.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class UpdateFileRequestDto {
    private List<Long> fileNoList;
    private List<MultipartFile> multipartFileList;
    private Long boardNo;
}
