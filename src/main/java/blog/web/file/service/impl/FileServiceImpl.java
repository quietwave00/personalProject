package blog.web.file.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import blog.domain.entity.File;
import blog.web.file.repository.FileRepository;
import blog.web.file.service.FileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;



    @Override
    public List<String> upload(List<MultipartFile> multipartFileList) throws IOException {
        List<String> filePathList = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFileList) {
            String originalName = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(size);

            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String filePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); //접근 가능 url
            filePathList.add(filePath);

            //저장
            new File().createFile();
        }

        return filePathList;
    }
}
