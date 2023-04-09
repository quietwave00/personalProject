package blog.web.spotify.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.service.SpotifyService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotifyApiController {

    private final SpotifyService spotifyService;

    //검색
    @GetMapping("/musics/{keyword}")
    public ApiResult<List<SearchResponseDto>> search(@PathVariable("keyword") String keyword) {
        List<SearchResponseDto> searchResponseDto = spotifyService.search(keyword);
        return ApiUtils.success(searchResponseDto);
    }

    //상세 페이지
    @GetMapping("/musics/board/{trackId}")
    public ApiResult<DetailsTrackResponseDto> detail(@PathVariable("trackId") String trackId) throws IOException, ParseException, SpotifyWebApiException {
        DetailsTrackResponseDto detailsTrackResponseDto = spotifyService.detail(trackId);
        return ApiUtils.success(detailsTrackResponseDto);
    }


}
