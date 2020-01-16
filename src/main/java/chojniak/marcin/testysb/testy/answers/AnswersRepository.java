package chojniak.marcin.testysb.testy.answers;

import chojniak.marcin.testysb.testy.question.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AnswersRepository extends CrudRepository<Answers, Long> {
    Set<Answers> findByQuestion(Question question);

    Set<Answers> findAll();
}
