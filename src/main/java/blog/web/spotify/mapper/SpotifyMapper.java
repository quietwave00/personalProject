package blog.web.spotify.mapper;

import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpotifyMapper {

    public SearchResponseDto toSearchDto(String trackId, String artistName, String title, String albumName, String imageUrl) {
        return SearchResponseDto.builder()
                .trackId(trackId)
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .build();
    }

    public DetailsTrackResponseDto toDetailsDto(String artistName, String title, String albumName, String imageUrl) {
        return DetailsTrackResponseDto.builder()
                .artistName(artistName)
                .title(title)
                .albumName(albumName)
                .imageUrl(imageUrl)
                .build();
    }
}
