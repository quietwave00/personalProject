package blog.web.comment.repository;

import blog.domain.entity.Board;
import blog.domain.entity.Comment;
import blog.domain.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentNo(Long commentNo);

    List<Comment> findByBoardAndStatus(Board findBoard, Status y);
}
