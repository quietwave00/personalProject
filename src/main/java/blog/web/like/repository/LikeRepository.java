package blog.web.like.repository;

import blog.domain.entity.Board;
import blog.domain.entity.Like;
import blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByBoardAndUser(Board findBoard, User findUser);

    void deleteByLikeNo(Long likeNo);
}
