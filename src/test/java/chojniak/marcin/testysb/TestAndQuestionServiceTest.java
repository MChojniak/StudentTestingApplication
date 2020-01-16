//package chojniak.marcin.testysb;
//
//import chojniak.marcin.testysb.testy.TestService;
//import chojniak.marcin.testysb.testy.question.Question;
//import chojniak.marcin.testysb.testy.question.QuestionService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest
//        .WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//public class TestAndQuestionServiceTest {
//    @Autowired
//    private TestService testService;
//    @Autowired
//    private QuestionService questionService;
//
//    @Before
//    public void initDB(){
//        {
//            questionService.addQuestion("Co pije krowa?", 5);
//            questionService.addQuestion("Co pije slon?", 7);
//        }
//        {
//            testService.addTest("Nowy test");
//        }
//
//    }
//
//    @Test
//    public void testOfAService(){
//
//    }
//}
