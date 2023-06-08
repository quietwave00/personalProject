package blog.web.mypage.service;

import blog.domain.entity.*;
import blog.exception.ErrorCode;
import blog.jwt.UserContextHolder;
import blog.utils.dto.ApiError;
import blog.web.board.mapper.BoardMapper;
import blog.web.board.repository.BoardRepository;
import blog.web.comment.repository.CommentRepository;
import blog.web.like.repository.LikeRepository;
import blog.web.mypage.controller.dto.response.BoardListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.CommentListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.LikedListOfMyPageResponseDto;
import blog.web.mypage.mapper.MyPageMapper;
import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import blog.web.spotify.service.SpotifyService;
import blog.web.user.repository.UserRepository;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final SpotifyService spotifyService;
    private final MyPageMapper myPageMapper;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    public List<BoardListOfMyPageResponseDto> selectBoards() throws IOException, ParseException, SpotifyWebApiException {
        User findUser = findUser(UserContextHolder.getUserId());
        List<Board> findBoardList = boardRepository.findByUserAndStatusOrderByBoardNoDesc(findUser, Status.Y);
        List<BoardListOfMyPageResponseDto> boardListOfMyPageResponseDtoList = new ArrayList<>();
        for(Board board : findBoardList) {
            DetailsTrackResponseDto detailsTrackResponseDto = spotifyService.detail(board.getTrackId());
            BoardListOfMyPageResponseDto boardListOfMyPageResponseDto = myPageMapper.toBoardListDto(board, detailsTrackResponseDto);

            boardListOfMyPageResponseDtoList.add(boardListOfMyPageResponseDto);
        }
        return boardListOfMyPageResponseDtoList;
    }

    public List<CommentListOfMyPageResponseDto> selectComments() {
        User findUser = findUser(UserContextHolder.getUserId());
        List<Comment> findCommentList = commentRepository.findByUserAndStatusOrderByCommentNoDesc(findUser, Status.Y);
        List<CommentListOfMyPageResponseDto> commentListOfMyPageResponseDtoList = new ArrayList<>();

        for(Comment comment : findCommentList) {
            CommentListOfMyPageResponseDto commentListOfMyPageResponseDto = myPageMapper.toCommentListDto(comment);
            commentListOfMyPageResponseDtoList.add(commentListOfMyPageResponseDto);
        }
        return commentListOfMyPageResponseDtoList;
    }

    public List<LikedListOfMyPageResponseDto> selectLiked() {
        User findUser = findUser(UserContextHolder.getUserId());
        List<Like> findLikeList = likeRepository.findByUser(findUser);
        List<LikedListOfMyPageResponseDto> likedBoardDtoList = new ArrayList<>();
        for(Like like : findLikeList) {
            Board findBoard = like.getBoard();
            LikedListOfMyPageResponseDto likedListOfMyPageResponseDto = myPageMapper.toLikedListOfMyPageDto(findBoard);
            likedBoardDtoList.add(likedListOfMyPageResponseDto);
        }
        return likedBoardDtoList;
    }


    //단일 메소드
    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
    }


}
