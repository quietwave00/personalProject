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

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SpotifyConfig.accessToken())
            .build();

    private final SpotifyMapper mapper;
    public List<SearchResponseDto> search(String keyword) {

        String albumName = "";
        String artistName = "";
        String imageUrl = "";
        List <SearchResponseDto> searchResponseDtoList = new ArrayList<>();
        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(keyword)
                    .limit(10)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();


            for (Track track : tracks) {
                AlbumSimplified album = track.getAlbum();

                ArtistSimplified[] artistArray = album.getArtists();
                artistName = artistArray[0].getName();
                System.out.println("artist: " + artistName);

                Image[] imageArray = album.getImages();
                for (Image image : imageArray) {
                    imageUrl = imageArray[0].getUrl();
                    System.out.println("imageUrl: " + imageUrl);
                }


                albumName = album.getName();
                System.out.println("albumName: " + albumName);

                searchResponseDtoList.add(mapper.toSearchDto(artistName, albumName, imageUrl));

            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }
}
