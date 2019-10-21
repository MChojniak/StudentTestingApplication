package chojniak.marcin.testysb;

import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import chojniak.marcin.testysb.users.UserQuestion;
import chojniak.marcin.testysb.users.UserQuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserQuestionAndQuestionServices {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserQuestionService userQuestionService;

    @Before
    public void initDB(){
        {
            questionService.addQuestion("Co to krowa",3);
        }
        {
            userQuestionService.addUserQuestion();
        }
    }

    @Test
    public void servicesTest(){

    }
}
