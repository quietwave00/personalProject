package blog.web.file.controller;

import blog.web.file.controller.dto.response.UploadResponseDto;
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
@RequestMapping("/blog/user")
public class FileController {

    private final FileService fileService;

    @PostMapping("/files/{boardNo}")
    public ApiResult<List<UploadResponseDto>> upload(@RequestPart List<MultipartFile> multipartFileList, @PathVariable("boardNo") Long boardNo) throws IOException {
        List<UploadResponseDto> filePathList = fileService.upload(multipartFileList, boardNo);
        return ApiUtils.success(filePathList);
    }






}
