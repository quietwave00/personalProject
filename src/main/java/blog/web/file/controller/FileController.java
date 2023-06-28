package blog.web.file.controller;

import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.service.FileFacade;
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
    private final FileFacade fileFacade;

    @PostMapping("/files/{boardNo}")
    public ApiResult<List<UploadFileResponseDto>> upload(@RequestPart List<MultipartFile> multipartFileList, @PathVariable("boardNo") Long boardNo) throws IOException {
        List<UploadFileResponseDto> uploadFileResponseDtoList = fileFacade.upload(multipartFileList, boardNo);
        return ApiUtils.success(uploadFileResponseDtoList);
    }

    @PatchMapping("/files")
    public ApiResult<List<UpdateFileResponseDto>> update(@RequestPart(value = "files") List<MultipartFile> multipartFileList, @RequestPart(value = "dto") UpdateFileRequestDto updateFileRequestDto) throws IOException {
        System.out.println("multipart: " + multipartFileList.get(0) +", dto: " + updateFileRequestDto.getBoardNo());
        List<UpdateFileResponseDto> updateFileResponseDtoList = fileFacade.update(multipartFileList, updateFileRequestDto);
        return ApiUtils.success(updateFileResponseDtoList);
    }

    @GetMapping("/files/main/{boardNo}")
    public ApiResult<List<GetMainFileByBoardResponseDto>> getFiles(@PathVariable("boardNo") Long boardNo) {
        List<GetMainFileByBoardResponseDto> getFileByBoardResponseDtoList = fileService.getFiles(boardNo);
        return ApiUtils.success(getFileByBoardResponseDtoList);
    }








}
