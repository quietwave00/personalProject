package blog.web.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import blog.web.file.service.FileService;
import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public ApiResult<List<String>> upload(@RequestPart List<MultipartFile> multipartFileList) throws IOException {
        List<String> filePathList = fileService.upload(multipartFileList);
        return ApiUtils.success(filePathList);
    }






}
