package blog.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;

    //--비즈니스 로직--
    public Like saveLike(Board board, User user) {
        this.board = board;
        this.user = user;
        return this;
    }

}
