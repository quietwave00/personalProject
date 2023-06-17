package blog.web.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
