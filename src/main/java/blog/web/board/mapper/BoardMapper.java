package blog.web.board.mapper;

import blog.domain.entity.Hashtag;
import blog.web.board.controller.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.domain.entity.Board;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Slf4j
@Component
public class BoardMapper {


    public Board toEntity(CreateBoardRequestDto dto) {
        return Board.builder()
                .content(dto.getContent())
                .trackId(dto.getTrackId())
                .build();
    }

    public CreateBoardResponseDto toCreateDto(Board board) {
        return CreateBoardResponseDto.builder()
                .boardNo(board.getBoardNo())
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .tagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .build();
    }

    public UpdateBoardResponseDto toUpdateDto(Board board) {
        return UpdateBoardResponseDto.builder()
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .modifiedDate(board.getModifiedDate())
                .build();
    }

    public DetailsBoardResponseDto toDetailsDto(Board board) {
        return DetailsBoardResponseDto.builder()
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .count(board.getCount())
                .hashtagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .build();
    }

    public DeleteBoardResponseDto toDeleteDto(Board board) {
        return DeleteBoardResponseDto.builder()
                .boardNo(board.getBoardNo())
                .build();
    }

    public BoardListResponseDto toBoardListDto(Board board) {
        return BoardListResponseDto.builder()
                .boardNo(board.getBoardNo())
                .content(board.getContent())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .nickname(board.getUser().getNickname())
                .build();
    }

    public BoardOfTrackResponseDto toBoardOfTrackDto(Board board) {
        return BoardOfTrackResponseDto.builder()
                .boardNo(board.getBoardNo())
                .tagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .content(board.getContent())
                .build();

    }

    public GetBoardByHashtagResponseDto toBoardByHashtagDto(Board board) {
        return GetBoardByHashtagResponseDto.builder()
                .boardNo(board.getBoardNo())
                .tagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .content(board.getContent())
                .build();
    }
}
