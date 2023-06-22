package blog.web.file.controller;

import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
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
    public ApiResult<List<UploadFileResponseDto>> upload(@RequestPart List<MultipartFile> multipartFileList, @PathVariable("boardNo") Long boardNo) throws IOException {
        List<UploadFileResponseDto> uploadFileResponseDtoList = fileService.upload(multipartFileList, boardNo);
        return ApiUtils.success(uploadFileResponseDtoList);
    }

    @PatchMapping("/files")
    public ApiResult<List<UpdateFileResponseDto>> update(@RequestBody UpdateFileRequestDto updateFileRequestDto) throws IOException {
        List<UpdateFileResponseDto> updateFileResponseDtoList = fileService.update(updateFileRequestDto);
        return ApiUtils.success(updateFileResponseDtoList);
    }

    @GetMapping("/files/main/{boardNo}")
    public ApiResult<List<GetMainFileByBoardResponseDto>> getFiles(@PathVariable("boardNo") Long boardNo) {
        List<GetMainFileByBoardResponseDto> getFileByBoardResponseDtoList = fileService.get(boardNo);
        return ApiUtils.success(getFileByBoardResponseDtoList);
    }








}
