package blog.web.mypage.mapper;

import blog.domain.entity.Board;
import blog.domain.entity.Hashtag;
import blog.web.mypage.controller.dto.response.BoardListOfMyPageResponseDto;
import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MyPageMapper {

    public BoardListOfMyPageResponseDto toBoardListDto(Board board, DetailsTrackResponseDto detailsTrackResponseDto) {
        return BoardListOfMyPageResponseDto.builder()
                .boardNo(board.getBoardNo())
                .content(board.getContent())
                .hashtagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .title(detailsTrackResponseDto.getTitle())
                .build();
    }
}
