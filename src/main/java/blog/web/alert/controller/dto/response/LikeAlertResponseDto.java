package blog.web.alert.controller.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LikeAlertResponseDto {
    private String nickname;
    private String title;
}
