package blog.web.alert.repository;

import blog.domain.entity.Alert;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends ReactiveCrudRepository<Alert, String> {

}
