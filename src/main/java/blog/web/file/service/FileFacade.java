package blog.web.file.service;

import blog.domain.entity.Board;
import blog.web.board.service.BoardService;
import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.service.dto.SaveFileDto;
import blog.web.file.service.util.S3ClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileFacade {


    private final FileService fileService;
    private final BoardService boardService;
    private final S3ClientUtils s3ClientUtils;


    public List<UploadFileResponseDto> upload(List<MultipartFile> multipartFileList, Long boardNo) throws IOException {
        List<SaveFileDto> saveFileDtoList = s3ClientUtils.getS3File(multipartFileList);
        Board findBoard = boardService.findBoard(boardNo);
        return fileService.upload(saveFileDtoList, findBoard);
    }
}
