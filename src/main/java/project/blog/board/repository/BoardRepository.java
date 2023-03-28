package project.blog.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.blog.domain.entity.Board;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByBoardNo(Long boardNo);
}
