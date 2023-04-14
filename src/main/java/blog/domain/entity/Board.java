package blog.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

    @Lob
    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ColumnDefault("0")
    private Integer count;

    private String trackId;

    @OneToMany(mappedBy = "board")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<File> file = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList = new ArrayList<>();

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

    public void addUser(User user) {
        this.user = user;
        user.getBoard().add(this);
    }

    public void addHashtag(List<Hashtag> hashtagList) {
        this.hashtagList = hashtagList;
        for(Hashtag hashtag : hashtagList) {
            hashtag.setBoard(this);
        }
    }

    //--비즈니스 로직--
    //글 수정
    public void update(String content) {
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    //조회수
    public void updateCount() {
        this.count += 1;
    }

    //게시글 삭제
    public void deleteBoard() {
        this.status = Status.N;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardNo=" + boardNo +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", status=" + status +
                ", count=" + count +
                ", trackId='" + trackId + '\'' +
                ", commentList=" + commentList +
                ", user=" + user +
                ", file=" + file +
                ", hashtagList=" + hashtagList +
                '}';
    }
}
