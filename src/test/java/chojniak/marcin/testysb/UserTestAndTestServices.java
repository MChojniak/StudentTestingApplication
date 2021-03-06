package chojniak.marcin.testysb;

import chojniak.marcin.testysb.testy.TestService;
import chojniak.marcin.testysb.users.UserQuestion;
import chojniak.marcin.testysb.users.UserQuestionService;
import chojniak.marcin.testysb.users.UserTest;
import chojniak.marcin.testysb.users.UserTestService;
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
public class UserTestAndTestServices {
    @Autowired
    UserTestService userTestService;
    @Autowired
    TestService testService;
    @Autowired
    UserQuestionService userQuestionService;

    @Before
    public void initDB() {
        testService.addTest("test"); // id 1
        userTestService.addUserTest(); // id 2
        userQuestionService.addUserQuestion(); // id 3
    }

    @Test
    public void testOfServicesWithUserQuestion() {
        userTestService.findAllUserTests();
        UserTest userTest = userTestService.findUserTestById((long) 2);
        Set<UserQuestion> userQuestions = userQuestionService.addUserTestToUserQuestion(userTest, (long) 3);
        assertThat(userQuestions.size()).isEqualTo(1);
    }
}
