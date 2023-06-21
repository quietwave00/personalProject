package blog.web.file.repository;

import blog.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.entity.File;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    Optional<List<File>> findByBoard(Board findBoard);

    Optional<File> findByFileNo(Long fileNo);
}
