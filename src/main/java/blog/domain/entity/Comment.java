package blog.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdDate;

    private LocalDateTime modifiedDate;

    private Status status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @PrePersist
    public void prePersist(){
        this.status = this.status == null ? Status.Y : this.status;
    }

    //==연관관계 메소드==
    public void addBoard(Board board) {
        this.board = board;
        board.getCommentList().add(this);
    }

    public void addUser(User user) {
        this.user = user;
        user.getComment().add(this);
    }

    //--비즈니스 로직--
    //댓글 수정
    public void update(String content) {
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }



}
