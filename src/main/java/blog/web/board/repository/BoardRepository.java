package blog.web.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.entity.Board;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByBoardNo(Long boardNo);
}
