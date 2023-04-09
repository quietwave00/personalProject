package blog.web.spotify.service;

import blog.config.SpotifyConfig;
import blog.web.spotify.controller.dto.response.DetailsTrackResponseDto;
import blog.web.spotify.controller.dto.response.SearchResponseDto;
import blog.web.spotify.mapper.SpotifyMapper;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.search.SearchItemRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyMapper mapper;


    public List<SearchResponseDto> search(String keyword) {
        SpotifyApi spotifyApi = getSpotifyApi();
        List <SearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(keyword)
                    .limit(10)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String trackId = track.getId();
                String title = track.getName();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();


                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(mapper.toSearchDto(trackId, artistName, title, albumName, imageUrl));
            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }


    public DetailsTrackResponseDto detail(String trackId) throws IOException, ParseException, SpotifyWebApiException {
        SpotifyApi spotifyApi = getSpotifyApi();
        GetTrackRequest request = spotifyApi.getTrack(trackId).build();
        Track searchTrack = request.execute();
        ConcurrentHashMap<String, String> searchTrackData = getTrackData(searchTrack);

        return mapper.toDetailsDto(searchTrackData.get("artistName"), searchTrackData.get("title"), searchTrackData.get("albumName"), searchTrackData.get("imageUrl"));
    }







    //단일 메소드
    private SpotifyApi getSpotifyApi() {
        return new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.accessToken())
                .build();
    }

    private ConcurrentHashMap<String, String> getTrackData(Track track) {
        ConcurrentHashMap<String, String> trackData = new ConcurrentHashMap<>();

        //타이틀
        String title = track.getName();
        trackData.put("title", title);

        //아티스트
        ArtistSimplified[] artists = track.getArtists();
        String artistName = artists[0].getName();
        trackData.put("artistName", artistName);

        //앨범
        AlbumSimplified album = track.getAlbum();
        String albumName = album.getName();
        trackData.put("albumName", albumName);

        //이미지
        Image[] images = album.getImages();
        String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";
        trackData.put("imageUrl", imageUrl);

        return trackData;
    }



}
