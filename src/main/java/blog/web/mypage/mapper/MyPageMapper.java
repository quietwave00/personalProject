package blog.web.mypage.mapper;

import blog.domain.entity.Board;
import blog.domain.entity.Comment;
import blog.domain.entity.Hashtag;
import blog.web.mypage.controller.dto.response.BoardListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.CommentListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.LikedListOfMyPageResponseDto;
import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyPageMapper {

    public BoardListOfMyPageResponseDto toBoardListDto(Board board, DetailsTrackResponseDto detailsTrackResponseDto) {
        return BoardListOfMyPageResponseDto.builder()
                .boardNo(board.getBoardNo())
                .content(board.getContent())
                .hashtagList(board.getHashtagList().stream().map(Hashtag::getName).collect(Collectors.toList()))
                .title(detailsTrackResponseDto.getTitle())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .build();
    }

    public CommentListOfMyPageResponseDto toCommentListDto(Comment comment) {
        return CommentListOfMyPageResponseDto.builder()
                .boardNo(comment.getBoard().getBoardNo())
                .commentNo(comment.getCommentNo())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate().toLocalDateTime())
                .build();
    }

    public LikedListOfMyPageResponseDto toLikedListOfMyPageDto(Board board) {
        return LikedListOfMyPageResponseDto.builder()
                .boardNo(board.getBoardNo())
                .content(board.getContent())
                .nickname(board.getUser().getNickname())
                .build();
    }
}
