package project.blog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import project.blog.board.controller.dto.request.CreateBoardRequestDto;
import project.blog.board.controller.dto.response.CreateBoardResponseDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

    private Timestamp modifiedDate;

    @ColumnDefault("0")
    private Integer count; //조회수

    @OneToMany(mappedBy = "board")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<File> file = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.count = this.count == null ? 0 : this.count;
    }



    //--연관관계 메소드--
    public void addComment(Comment comment) {
        this.commentList.add(comment);
        comment.setBoard(this);
    }

    public Board addUser(User user) {
        this.user = user;
        user.getBoard().add(this);

        return this;
    }


    //--toDto--
    public static CreateBoardResponseDto toCreateDto(Board board) {
        return CreateBoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .build();
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardNo=" + boardNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", count=" + count +
                ", commentList=" + commentList +
                ", user=" + user +
                ", file=" + file +
                '}';
    }
}
