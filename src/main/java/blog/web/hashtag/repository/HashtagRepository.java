package blog.web.hashtag.repository;

import blog.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<List<Hashtag>> findByName(String hashtag);
}
