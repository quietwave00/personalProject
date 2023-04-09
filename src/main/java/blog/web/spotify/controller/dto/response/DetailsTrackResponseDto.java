package blog.web.spotify.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DetailsTrackResponseDto {
    private String title;
    private String albumName;
    private String artistName;
    private String imageUrl;
}
