package blog.web.alert.controller.dto.response;

import blog.domain.entity.AlertCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AlertResponseDto {
    private String alertId;
    private String nickname;
    private String title;
    private String alertCategory;
}
