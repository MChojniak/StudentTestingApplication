package chojniak.marcin.testysb;

import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AnswerAndQuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswersService answersService;

    @Before
    public void initDB(){
        {
            questionService.addQuestion("Czy marcin jest bystrzak",
                    10);
            questionService.addQuestion("Woda czy browar",
                    100);
        }
    }

    @Test
    public void testOfAService(){

    }

}
