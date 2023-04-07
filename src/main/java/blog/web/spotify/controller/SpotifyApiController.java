package blog.web.spotify.controller;

import blog.web.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpotifyApiController {

    private final SpotifyService spotifyService;

    @GetMapping("/musics/{keyword}")
    public /*ApiResult<SearchResponseDto>*/ void search(@PathVariable("keyword") String keyword) {
        spotifyService.search(keyword);
//        return ApiUtils.success(searchResponseDto);
    }


}
