package chojniak.marcin.testysb.testy.answers;

import chojniak.marcin.testysb.testy.question.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswersService {
    private Logger logger = LoggerFactory.getLogger(AnswersService.class);
    private AnswersRepository answersRepository;

    @Autowired
    public AnswersService(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    public Answers addAnswer(Answers answers) {
        return answersRepository.save(answers);

    }

    public Set<Answers> addAnswerToQuestion(Answers answers, Question question) {
        answers.setQuestion(question);
        question.getAnswers().add(answers);
        answersRepository.save(answers);
        return question.getAnswers();
    }


    public Set<Answers> findQuestionAnswers(Question question) {
        return answersRepository.findByQuestion(question);
    }

    public Answers findAnswerById(Long id) {
        return answersRepository.findById(id).get();
    }


    public Set<Answers> deleteAnswerFromQuestion(Answers answers, Question question) {
        Set<Answers> answersSet = question.getAnswers();
        answersSet = answersSet.stream().filter(u -> !u.getId().equals(answers.getId())).collect(Collectors.toSet());
        question.setAnswers(answersSet);
        answersRepository.save(answers);
        return question.getAnswers();

    }

    public Set<Answers> findAll() {
        return answersRepository.findAll();
    }
}
