package chojniak.marcin.testysb.testy.question;

import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {
    private Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question addQuestion(String questionContent, int questionValue){
        Question question = new Question();
        question.setQuestionContent(questionContent);
        question.setQuestionValue(questionValue);
        Question q1 = questionRepository.save(question);
        logger.info("addQuestion -- {} , {} , {}",q1.getId(),q1.getQuestionContent(),q1.getQuestionValue());
        return q1;
    }

    public Question addQuestionToTest(Test test, Long id){
        Question question = findById(id);
        question.setTest(test);
        test.getQuestions().add(question);
        return  questionRepository.save(question);
    }

    public Question findById(Long id) {
        Question question = questionRepository.findById(id).get();
        question.getAnswers().size();
        return question;
    }

    public Set<Question> findAllQuestions(){
        return questionRepository.findAll();
    }

    public Question findByQuestionContent(String qContent, Test test){
        Set<Question> questions = questionRepository.findByQuestionContent(qContent);
        Question question = null;
        if(questions.size() == 1)
            for(Question a : questions) {
                question = a;
                return a;
            }
        else if (questions.size() > 0){
            for(Question a : questions)
                if(a.getTest().getTestName() == test.getTestName()) {
                    question = a;
                    return a;
                }
        }
        return question;
    }


    public Set<Question> findQuestionsOfTest(Test test){
        return questionRepository.findByTest(test);
    }

    public Set<Question> deleteFromTest(Test test, Long id){
        Question question = findById(id);
        Set<Question> questions = test.getQuestions();
        questions = questions.stream().filter(u->u.getId()!=question.getId()).collect(Collectors.toSet());
        test.setQuestions(questions);
        questionRepository.save(question);
        return questions;
    }

    public Question addQuestion(Question newQuestion) {
        return questionRepository.save(newQuestion);
    }

//    public Question addUserAnswer(Long questionId,Long answerId){
//        Question question = findById(questionId);
//        Answers answer = AnswersService.findBy
//        question.setAnswerByUser();
//    }
}
