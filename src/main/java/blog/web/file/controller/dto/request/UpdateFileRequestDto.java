package blog.web.file.controller.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class UpdateFileRequestDto {
    private Long boardNo;
    private List<Long> fileNoList;
}
