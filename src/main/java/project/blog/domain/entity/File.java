package project.blog.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNo;

    private String fileName;

    private Integer fileLevel;

    @ManyToOne
    @JoinColumn(name = "file_no")
    private Board board;



}
