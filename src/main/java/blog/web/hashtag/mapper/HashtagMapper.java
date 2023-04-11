package blog.web.hashtag.mapper;

import blog.domain.entity.Hashtag;
import org.springframework.stereotype.Component;

@Component
public class HashtagMapper {
    public Hashtag toEntity(String hashtag) {
        return Hashtag.builder()
                .name(hashtag)
                .build();
    }
}
