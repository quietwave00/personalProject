package blog.web.file.service.util;

import blog.utils.dto.ApiError;
import blog.web.file.service.dto.SaveFileDto;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class S3ClientUtils {
    public final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public S3ClientUtils(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public List<SaveFileDto> getS3File(List<MultipartFile> multipartFileList) throws IOException {
        List<SaveFileDto> saveFileDtoList = new ArrayList<>();
        for(int i = 0; i < multipartFileList.size(); i++) {
            MultipartFile multipartFile = multipartFileList.get(i);
            String originalName = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(size);

            amazonS3Client.putObject(
                    new PutObjectRequest(bucketName, originalName, multipartFile.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            saveFileDtoList.add(new SaveFileDto(i, originalName, amazonS3Client.getUrl(bucketName, originalName).toString()));
        }
        return saveFileDtoList;
    }

    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(bucketName, fileName);
    }




}
