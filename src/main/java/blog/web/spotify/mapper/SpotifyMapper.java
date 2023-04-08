package blog.web.spotify.mapper;

import blog.web.spotify.controller.dto.response.SearchResponseDto;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpotifyMapper {

    public SearchResponseDto toSearchDto(String artistName, String albumName, String imageUrl) {
        return SearchResponseDto.builder()
                .artistName(artistName)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .build();
    }
}
