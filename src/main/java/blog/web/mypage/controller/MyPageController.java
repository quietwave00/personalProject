package blog.web.mypage.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.mypage.controller.dto.response.BoardListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.CommentListOfMyPageResponseDto;
import blog.web.mypage.controller.dto.response.LikedListOfMyPageResponseDto;
import blog.web.mypage.service.MyPageService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/user/page")
public class MyPageController {

    private final MyPageService myPageService;

    //내가 쓴 게시글 조회
    @GetMapping("/boards")
    public ApiResult<List<BoardListOfMyPageResponseDto>> selectBoards() throws IOException, ParseException, SpotifyWebApiException {
        List<BoardListOfMyPageResponseDto> boardListOfMyPageResponseDto = myPageService.selectBoards();
        return ApiUtils.success(boardListOfMyPageResponseDto);
    }

    //내가 쓴 댓글 조회
    @GetMapping("/comments")
    public ApiResult<List<CommentListOfMyPageResponseDto>> selectComments() {
        List<CommentListOfMyPageResponseDto> commentListOfMyPageResponseDtoList = myPageService.selectComments();
        return ApiUtils.success(commentListOfMyPageResponseDtoList);
    }

    //내가 좋아요 한 게시글 조회
    @GetMapping("/likes")
    public ApiResult<List<LikedListOfMyPageResponseDto>> selectLiked() {
        List<LikedListOfMyPageResponseDto> likedListOfMyPageResponseDtoList = myPageService.selectLiked();
        return ApiUtils.success(likedListOfMyPageResponseDtoList);
    }

}
