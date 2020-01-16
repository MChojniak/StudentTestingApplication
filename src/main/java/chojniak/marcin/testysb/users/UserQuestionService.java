package chojniak.marcin.testysb.users;

import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserQuestionService {
    private UserQuestionRepository userQuestionRepository;

    @Autowired
    public UserQuestionService(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    public UserQuestion addUserQuestion() {
        UserQuestion userQuestion = new UserQuestion();
        return userQuestionRepository.save(userQuestion);

    }

    public Set<UserQuestion> addUserQuestionsToUserTest(Set<Question> questions, UserTest userTest) {
        Set<UserQuestion> userQuestions = new HashSet<>();


        List<Question> shuffledQuestions = new ArrayList<>(questions);
        Collections.shuffle(shuffledQuestions);
        int i = 0;
        for (Question q : shuffledQuestions) {
            UserQuestion a = new UserQuestion();
            a.setQuestion(q);
            a.setUser(userTest.getUser());
            a.setUserTest(userTest);
            userQuestions.add(a);
            userQuestionRepository.save(a);
            if (i == 9) break;
            i++;
        }

        return userQuestions;
    }


    public UserQuestion addUserQuestion(UserTest userTest, User user, Question question) {
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setUserTest(userTest);
        userQuestion.setUser(user);
        userQuestion.setQuestion(question);
        userQuestion = userQuestionRepository.save(userQuestion);
        return userQuestion;
    }

    public UserQuestion findById(Long id) {
        UserQuestion userQuestion = userQuestionRepository.findById(id).get();
        return userQuestion;
    }

    public Set<UserQuestion> addUserTestToUserQuestion(UserTest userTest, Long id) {
        UserQuestion userQuestion = findById(id);
        userQuestion.setUserTest(userTest);
        userTest.getUserQuestions().add(userQuestion);
        userQuestionRepository.save(userQuestion);
        return userTest.getUserQuestions();
    }


    public void saveAnsweredId(UserQuestion q, Long answerId) {
        q.setAnswerId(answerId);
        userQuestionRepository.save(q);
    }
}
