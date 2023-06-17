package blog.web.file.service.impl;

import blog.domain.entity.Board;
import blog.domain.entity.FileLevel;
import blog.exception.ErrorCode;
import blog.utils.dto.ApiError;
import blog.web.board.repository.BoardRepository;
import blog.web.file.controller.dto.response.UploadResponseDto;
import blog.web.file.mapper.FileMapper;
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
    private final BoardRepository boardRepository;
    private final FileMapper fileMapper;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;



    @Override
    public List<UploadResponseDto> upload(List<MultipartFile> multipartFileList, Long boardNo) throws IOException {
        List<UploadResponseDto> uploadResponseDtoList = new ArrayList<>();
        for(int i = 0; i < multipartFileList.size(); i++) {
            MultipartFile multipartFile = multipartFileList.get(i);
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
            //저장
            Board findBoard = findBoard(boardNo);
            File savedFile;
            File createFile;
            if(i == 0) {
                createFile = new File().createFile(originalName, FileLevel.MAIN, filePath, findBoard);
            } else {
                createFile = new File().createFile(originalName, FileLevel.SUB, filePath, findBoard);
            }
            savedFile = fileRepository.save(createFile);
            UploadResponseDto uploadResponseDto = fileMapper.toUploadDto(savedFile);
            uploadResponseDtoList.add(uploadResponseDto);
        }

        return uploadResponseDtoList;
    }

    //단일 메소드
    private Board findBoard(Long boardNo) {
        return boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
    }
}
