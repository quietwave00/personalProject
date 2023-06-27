package blog.web.file.service;

import blog.domain.entity.Board;
import blog.domain.entity.FileLevel;
import blog.exception.ErrorCode;
import blog.utils.dto.ApiError;
import blog.web.board.repository.BoardRepository;
import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.mapper.FileMapper;
import blog.web.file.service.dto.SaveFileDto;
import blog.web.file.service.util.S3ClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import blog.domain.entity.File;
import blog.web.file.repository.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final FileMapper fileMapper;

    private final S3ClientUtils s3Utils;

    public List<UploadFileResponseDto> upload(List<SaveFileDto> saveFileDtoList, Board board) throws IOException {
        List<File> files = fileMapper.toEntities(saveFileDtoList, board);
        List<File> savedFiles = fileRepository.saveAll(files);
        return fileMapper.toUploadDtoList(savedFiles);
    }

    public List<UpdateFileResponseDto> update(UpdateFileRequestDto updateFileRequestDto) throws IOException {
        Board findBoard = findBoard(updateFileRequestDto.getBoardNo());
        List<File> checkFileList = findFileByBoard(findBoard);

        //기존 게시물별 파일의 pk값
        List<Long> getFileNoByBoardList = checkFileList.stream()
                .map(File::getFileNo)
                .collect(Collectors.toList());

        //클라이언트가 삭제한 파일의 pk값(s3에는 추후에 삭제)
        for(int i = 0; i < updateFileRequestDto.getFileNoList().size(); i++) {
            if(!Objects.equals(getFileNoByBoardList.get(i), updateFileRequestDto.getFileNoList().get(i))) {
                Long fileNo = getFileNoByBoardList.get(i);
                findFile(fileNo).deleteFile();
            }
        }

        //새로운 file을 저장
//        List<MultipartFile> multipartFileList = updateFileRequestDto.getMultipartFileList();
//        List<UpdateFileResponseDto> updateFileResponseDtoList = new ArrayList<>();
//        List<SaveFileDto> saveFileDtoList = getS3File(multipartFileList);
//        for(SaveFileDto saveFileDto : saveFileDtoList) {
//            FileLevel fileLevel = (saveFileDto.getIndex() == 0) ? FileLevel.MAIN : FileLevel.SUB;
//            File createFile = new File().createFile(saveFileDto.getOriginalName(), fileLevel, saveFileDto.getFilePath(), findBoard);
//            File savedFile = fileRepository.save(createFile);
//            updateFileResponseDtoList.add(fileMapper.toUpdateDto(savedFile));
//        }
//        return updateFileResponseDtoList;
        return null;
    }

    public List<GetMainFileByBoardResponseDto> get(Long boardNo) {
        Board findBoard = findBoard(boardNo);
        List<File> findFileByBoardList = findFileByBoard(findBoard);
        List<GetMainFileByBoardResponseDto> getMainFileByBoardResponseDtoList = new ArrayList<>();
        for(File file : findFileByBoardList) {
            if(file.getFileLevel() == FileLevel.MAIN) {
                getMainFileByBoardResponseDtoList.add(fileMapper.toGetMainDto(file));
            }
        }
        return getMainFileByBoardResponseDtoList;
    }

    //단일 메소드
//    private List<SaveFileDto> getS3File(List<MultipartFile> multipartFileList) throws IOException {
//        List<SaveFileDto> saveFileDtoList = new ArrayList<>();
//        for(int i = 0; i < multipartFileList.size(); i++) {
//            MultipartFile multipartFile = multipartFileList.get(i);
//            String originalName = multipartFile.getOriginalFilename();
//            long size = multipartFile.getSize();
//
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentType(multipartFile.getContentType());
//            objectMetadata.setContentLength(size);
//
//            amazonS3Client.putObject(
//                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetadata)
//                            .withCannedAcl(CannedAccessControlList.PublicRead)
//            );
//            saveFileDtoList.add(new SaveFileDto(i, originalName, amazonS3Client.getUrl(S3Bucket, originalName).toString()));
//        }
//        return saveFileDtoList;
//    }

    private Board findBoard(Long boardNo) {
        return boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
    }

    private List<File> findFileByBoard(Board board) {
        return fileRepository.findByBoard(board)
                .orElseThrow(() -> new ApiError(ErrorCode.FILE_NOT_FOUND));
    }

    private File findFile(Long fileNo) {
        return fileRepository.findByFileNo(fileNo)
                .orElseThrow(() -> new ApiError(ErrorCode.FILE_NOT_FOUND));
    }
}
