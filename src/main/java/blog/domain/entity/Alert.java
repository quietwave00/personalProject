package blog.domain.entity;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public class Alert {
    private String nickname;

    private String title;

    private AlertCategory alertCategory;

}
