package project.blog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.blog.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByPassword(String password);
}
