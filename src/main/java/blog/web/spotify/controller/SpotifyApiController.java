package blog.web.spotify.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpotifyApiController {

    private final SpotifyService spotifyService;

    @GetMapping("/musics/{keyword}")
    public ApiResult<List<SearchResponseDto>> search(@PathVariable("keyword") String keyword) {
        List<SearchResponseDto> searchResponseDto = spotifyService.search(keyword);
        return ApiUtils.success(searchResponseDto);
    }


}
