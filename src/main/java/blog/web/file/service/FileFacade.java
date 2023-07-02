package blog.web.file.service;

import blog.domain.entity.Board;
import blog.domain.entity.File;
import blog.web.board.service.BoardService;
import blog.web.file.controller.dto.request.UpdateFileRequestDto;
import blog.web.file.controller.dto.response.UpdateFileResponseDto;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.service.dto.SaveFileDto;
import blog.web.file.service.util.S3ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileFacade {


    private final FileService fileService;
    private final BoardService boardService;
    private final S3ClientUtils s3ClientUtils;


    /*
     * 저장할 파일의 정보를 담을 dto와 해당하는 board를 반환한다.
     */
    public List<UploadFileResponseDto> upload(List<MultipartFile> multipartFileList, Long boardNo) throws IOException {
        List<SaveFileDto> saveFileDtoList = s3ClientUtils.getS3File(multipartFileList);
        Board findBoard = boardService.findBoard(boardNo);
        return fileService.upload(saveFileDtoList, findBoard);
    }

    /*
     * 수정할 파일의 정보를 담을 dto
     */
    public List<UpdateFileResponseDto> update(List<MultipartFile> multipartFileList, UpdateFileRequestDto updateFileRequestDto) throws IOException {
        List<SaveFileDto> saveFileDtoList = s3ClientUtils.getS3File(multipartFileList);
        Board findBoard = boardService.findBoard(updateFileRequestDto.getBoardNo());
        return fileService.update(saveFileDtoList, updateFileRequestDto, findBoard);
    }

}
