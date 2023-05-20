package blog.web.alert.repository;

import blog.domain.entity.Alert;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {

    Document findByNickname(String nickname);
}
