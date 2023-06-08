package blog.web.board.repository;

import blog.domain.entity.Status;
import blog.domain.entity.User;
import blog.web.board.controller.dto.response.BoardListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.entity.Board;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByBoardNo(Long boardNo);

    List<Board> findByStatusOrderByBoardNoDesc(Status status);

    List<Board> findByStatusAndTrackIdOrderByBoardNoDesc(Status status, String trackId);

    List<Board> findByUserAndStatusOrderByBoardNoDesc(User user, Status y);
}
