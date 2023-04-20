package blog.web.like.repository.impl;

import blog.domain.entity.Board;
import blog.domain.entity.Like;
import blog.web.like.repository.CustomLikeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class LikeRepositoryImpl implements CustomLikeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Board> countByBoardGroupByBoard() {
        return entityManager.createQuery("SELECT l.board, COUNT(l.board) FROM Like l GROUP BY l.board", Board.class)
                .getResultList();
    }
}
