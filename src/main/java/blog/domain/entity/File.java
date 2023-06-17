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

    @Enumerated(EnumType.STRING)
    private FileLevel fileLevel;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private Board board;

    public File createFile(String fileName, FileLevel fileLevel, String filePath, Board board) {
        return File.builder()
                .fileName(fileName)
                .fileLevel(fileLevel)
                .filePath(filePath)
                .board(board)
                .build();
    }



}
