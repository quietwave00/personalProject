package blog.web.mypage.repository;

import blog.domain.entity.Board;
import blog.domain.entity.Status;
import blog.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPageRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserAndStatusOrderByBoardNoDesc(User user, Status y);
}
