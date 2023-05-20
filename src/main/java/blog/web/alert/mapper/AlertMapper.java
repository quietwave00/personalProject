package blog.web.alert.mapper;

import blog.domain.entity.Alert;
import blog.domain.entity.AlertCategory;
import blog.domain.entity.Comment;
import blog.web.alert.controller.dto.response.AlertResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AlertMapper {

    public Alert commentToAlert(Comment comment) {
        return Alert.builder()
                .nickname(comment.getUser().getNickname())
                .title(comment.getBoard().getTrackId())
                .alertCategory(AlertCategory.COMMENTS)
                .build();
    }
    public AlertResponseDto toResponseDto(String id, String nickname, String title, String alertCategory) {
        return AlertResponseDto.builder()
                .alertId(id)
                .nickname(nickname)
                .title(title)
                .alertCategory(alertCategory)
                .build();
    }
}
