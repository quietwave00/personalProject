package project.blog.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.blog.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
