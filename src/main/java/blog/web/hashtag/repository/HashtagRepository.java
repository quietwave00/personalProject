package blog.web.hashtag.repository;

import blog.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

}
