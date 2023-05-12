package blog.web.comment.repository;

import blog.domain.entity.Board;
import blog.domain.entity.Comment;
import blog.domain.entity.Status;
import blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentNo(Long commentNo);

    List<Comment> findByBoardAndStatusAndParentIsNull(Board findBoard, Status status);

    List<Comment> findByUserAndStatusOrderByCommentNoDesc(User findUser, Status status);
}
