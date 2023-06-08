package blog.web.like.repository;

import blog.domain.entity.Board;
import blog.domain.entity.Like;
import blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>, CustomLikeRepository {
    Optional<Like> findByBoardAndUser(Board findBoard, User findUser);

    void deleteByLikeNo(Long likeNo);

    Long countByBoard(Board findBoard);

    Boolean existsByBoardAndUser(Board findBoard, User findUser);

    List<Like> findByUser(User findUser);
}
