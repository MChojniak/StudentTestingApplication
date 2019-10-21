package chojniak.marcin.testysb.testy.question;

import chojniak.marcin.testysb.testy.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Set<Question> findByQuestionContent(String qContent);
    Set<Question> findAll();
    Set<Question> findByTest(Test test);
}
