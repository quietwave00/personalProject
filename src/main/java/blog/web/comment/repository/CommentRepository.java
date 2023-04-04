package blog.web.comment.repository;

import blog.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentNo(Long commentNo);
}
