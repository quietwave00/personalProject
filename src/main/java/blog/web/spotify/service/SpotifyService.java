package blog.web.spotify.service;

import blog.config.SpotifyConfig;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.mapper.SpotifyMapper;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyMapper mapper;

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SpotifyConfig.accessToken())
            .build();


    public List<SearchResponseDto> search(String keyword) {
        List <SearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(keyword)
                    .limit(10)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String title = track.getName();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();


                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(mapper.toSearchDto(artistName, title, albumName, imageUrl));
            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }
}
