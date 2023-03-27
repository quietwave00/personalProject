package project.blog.file.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.blog.file.service.FileService;
import project.blog.utils.ApiUtils;
import project.blog.utils.dto.ApiResult;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private String S3Bucket = "bloguploading";

    private final FileService fileService;



    @PostMapping("/upload")
    public ApiResult<List<String>> upload(@RequestPart List<MultipartFile> multipartFileList) throws IOException {
        List<String> filePathList = fileService.upload(multipartFileList);
        return ApiUtils.success(filePathList);
    }





}
