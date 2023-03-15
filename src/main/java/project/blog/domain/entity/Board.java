package project.blog.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    private String content;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private String nickname;

    private Integer count; //조회수

    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

}
