package blog.web.like.repository;


import blog.domain.entity.Board;

import java.util.List;

public interface CustomLikeRepository {
    List<Board> countByBoardGroupByBoard();
}
