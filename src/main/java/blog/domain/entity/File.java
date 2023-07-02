package blog.domain.entity;

import blog.web.file.controller.dto.response.UploadFileResponseDto;
import blog.web.file.service.dto.SaveFileDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private Board board;

    /*
     *비즈니스 로직
     */

    public boolean hasMain() {
        return this.getFileLevel().equals(FileLevel.MAIN);
    }

    public void setFileLevelToMain() {
        this.fileLevel = FileLevel.MAIN;
    }



}
