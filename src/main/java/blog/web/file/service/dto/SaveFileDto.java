package blog.web.file.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveFileDto {
    private int index;
    private String originalName;
    private String filePath;
}
