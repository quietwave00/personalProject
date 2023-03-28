package project.blog.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.blog.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
