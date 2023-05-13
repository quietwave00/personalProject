package blog.web.alert.repository;

import blog.domain.entity.Alert;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {

}
