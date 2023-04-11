package blog.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagNo;

    private String name;

    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;
}
