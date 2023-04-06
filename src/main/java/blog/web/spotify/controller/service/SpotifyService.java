package blog.web.spotify.controller.service;

import blog.config.SpotifyConfig;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.mapper.SpotifyMapper;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SpotifyConfig.accesstoken())
            .build();

    private final SpotifyMapper mapper;
    public SearchResponseDto search(String keyword) {
        Track track = null;

        try {
            SearchItemRequest searchItemRequest = spotifyApi.searchItem(keyword, ModelObjectType.ARTIST.getType())
                    .market(CountryCode.US)
                    .limit(10)
                    .build();

            SearchResult searchResult = searchItemRequest.execute();
            System.out.println(searchResult.getTracks());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
