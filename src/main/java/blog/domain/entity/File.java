package blog.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNo;

    private String fileName;

    private Integer fileLevel;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private Board board;

    public File createFile() {
        return File.builder()
                .fileName(fileName)
                .fileLevel(fileLevel)
                .board(board)
                .build();


    }



}
