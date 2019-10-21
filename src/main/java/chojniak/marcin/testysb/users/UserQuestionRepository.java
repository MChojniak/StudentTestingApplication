package chojniak.marcin.testysb.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionRepository extends CrudRepository<UserQuestion, Long> {
}
