package blog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import blog.web.board.controller.dto.response.CreateBoardResponseDto;
import blog.web.board.controller.dto.response.DeleteBoardResponseDto;
import blog.web.board.controller.dto.response.DetailsBoardResponseDto;
import blog.web.board.controller.dto.response.UpdateBoardResponseDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ColumnDefault("0")
    private Integer count;

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
        this.status = this.status == null ? Status.Y : this.status;
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

    //--비즈니스 로직--
    //글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    //조회수
    public void updateCount() {
        this.count += 1;
    }

    //게시글 삭제
    public void deleteBoard(Board findBoard) {
        this.status = Status.N;
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

    public static UpdateBoardResponseDto toUpdateDto(Board board) {
        return UpdateBoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .modifiedDate(board.getModifiedDate())
                .build();
    }

    public static DetailsBoardResponseDto toDetailDto(Board board) {
        return DetailsBoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .createdDate(board.getCreatedDate().toLocalDateTime())
                .count(board.getCount())
                .build();

    }

    public static DeleteBoardResponseDto toDeleteDto(Board board) {
        return DeleteBoardResponseDto.builder()
                .boardNo(board.getBoardNo())
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
