package blog.web.spotify.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchResponseDto {
    private String artist;
    private String name;
    private String album;
    private String imageUrl;
}
