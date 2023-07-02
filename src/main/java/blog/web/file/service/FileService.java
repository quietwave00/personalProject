package blog.web.file.service;

import blog.domain.entity.Board;
import blog.exception.ErrorCode;
import blog.utils.dto.ApiError;
import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.GetMainFileByBoardResponseDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.mapper.FileMapper;
import blog.web.file.service.dto.SaveFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import blog.domain.entity.File;
import blog.web.file.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final RedisTemplate<String, Long> redisTemplate;


    public List<UploadFileResponseDto> upload(List<SaveFileDto> saveFileDtoList, Board board) throws IOException {
        List<File> files = fileMapper.toEntities(saveFileDtoList, board);
        List<File> savedFiles = fileRepository.saveAll(files);
        return fileMapper.toUploadDtoList(savedFiles);
    }

    public List<UpdateFileResponseDto> update(List<SaveFileDto> saveFileDtoList, UpdateFileRequestDto updateFileRequestDto, Board board) throws IOException {
        deleteFile(updateFileRequestDto.getFileNoList());
        List<File> files = fileMapper.toEntities(saveFileDtoList, board);
        List<File> savedFiles = fileRepository.saveAll(files);
        return fileMapper.toUpdateDtoList(savedFiles);
    }

    public List<GetMainFileByBoardResponseDto> getFiles(Long boardNo) {
//        Board findBoard = findBoard(boardNo);
//        List<File> findFileByBoardList = findFileByBoard(findBoard);
//        List<GetMainFileByBoardResponseDto> getMainFileByBoardResponseDtoList = new ArrayList<>();
//        for(File file : findFileByBoardList) {
//            if(file.getFileLevel() == FileLevel.MAIN) {
//                getMainFileByBoardResponseDtoList.add(fileMapper.toGetMainDto(file));
//            }
//        }
//        return getMainFileByBoardResponseDtoList;
        return null;
    }

    List<File> findFileByBoard(Board board) {
        return fileRepository.findByBoard(board)
                .orElseThrow(() -> new ApiError(ErrorCode.FILE_NOT_FOUND));
    }

    private File findFile(Long fileNo) {
        return fileRepository.findByFileNo(fileNo)
                .orElseThrow(() -> new ApiError(ErrorCode.FILE_NOT_FOUND));
    }

    private List<File> findFileByFileNo(List<Long> fileNoList) {
        return fileNoList.stream()
                .map(fileRepository::findByFileNo)
                .map(optionalFile -> optionalFile.orElseThrow(() -> new ApiError(ErrorCode.FILE_NOT_FOUND)))
                .collect(Collectors.toList());
    }

    /*
     * 기존 게시물 파일별 pk값
     */
    private List<Long> getFileNoByBoardToDelete(List<File> fileList) {
        return fileList.stream()
                .map(File::getFileNo)
                .collect(Collectors.toList());
    }

    /*
     * 클라이언트가 삭제한 파일의 pk 값과 비교하여 status 변경(s3에는 추후에 삭제)
     */
    private void deleteFile(List<Long> fileNoList) {
        List<File> fileList = findFileByFileNo(fileNoList);
        for(File file : fileList) {
            redisTemplate.opsForValue().set("deleteFileNoList", file.getFileNo());
            Object value = redisTemplate.opsForValue().get("deleteFileNoList");
            System.out.println("redis data: " + value);
        }
    }
}
