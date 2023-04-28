package blog.web.mypage.service;

import blog.domain.entity.Board;
import blog.domain.entity.Status;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.jwt.UserContextHolder;
import blog.utils.dto.ApiError;
import blog.web.board.service.BoardService;
import blog.web.hashtag.repository.HashtagRepository;
import blog.web.mypage.controller.dto.response.BoardListOfMyPageResponseDto;
import blog.web.mypage.mapper.MyPageMapper;
import blog.web.mypage.repository.MyPageRepository;
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

    private final MyPageRepository myPageRepository;
    private final UserRepository userRepository;
    private final SpotifyService spotifyService;
    private final MyPageMapper myPageMapper;

    public List<BoardListOfMyPageResponseDto> selectBoard() throws IOException, ParseException, SpotifyWebApiException {
        User findUser = findUser(UserContextHolder.getUserId());
        List<Board> findBoardList = myPageRepository.findByUserAndStatusOrderByBoardNoDesc(findUser, Status.Y);
        List<BoardListOfMyPageResponseDto> boardListOfMyPageResponseDtoList = new ArrayList<>();
        for(Board board : findBoardList) {
            DetailsTrackResponseDto detailsTrackResponseDto = spotifyService.detail(board.getTrackId());
            BoardListOfMyPageResponseDto boardListOfMyPageResponseDto = myPageMapper.toBoardListDto(board, detailsTrackResponseDto);

            boardListOfMyPageResponseDtoList.add(boardListOfMyPageResponseDto);
        }
        return boardListOfMyPageResponseDtoList;
    }

    //단일 메소드
    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
    }
}
