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

    private String filePath;

    @Enumerated(EnumType.STRING)
    private FileLevel fileLevel;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private Board board;

    @PrePersist
    public void prePersist(){
        this.status = this.status == null ? Status.Y : this.status;
    }

    /*
     *비즈니스 로직
     */
    public File createFile(String fileName, FileLevel fileLevel, String filePath, Board board) {
        return File.builder()
                .fileName(fileName)
                .fileLevel(fileLevel)
                .filePath(filePath)
                .board(board)
                .build();
    }

    public void deleteFile() {
        this.status = Status.N;
    }



}
