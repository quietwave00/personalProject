package blog.web.spotify.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.controller.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpotifyApiController {

    private final SpotifyService spotifyService;

    @GetMapping("/musics/{keyword}")
    public ApiResult<SearchResponseDto> search(@PathVariable("keyword") String keyword) {
        SearchResponseDto searchResponseDto = spotifyService.search(keyword);
        return ApiUtils.success(searchResponseDto);
    }


}
