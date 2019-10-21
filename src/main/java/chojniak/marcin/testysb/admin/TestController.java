package chojniak.marcin.testysb.admin;

import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.TestService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@SessionAttributes("testy")
@RequestMapping
public class TestController {
    private TestService testService;
    private QuestionService questionService;
    @Autowired
    public TestController(TestService testService,
                          QuestionService questionService) {
        this.testService = testService;
        this.questionService = questionService;
    }

    @GetMapping("/utworzTest")
    public String testsList(Model model){
        Set<Test> tests = testService.findAllTests();
        model.addAttribute("testy", tests);
        model.addAttribute("test", new Test());
        model.addAttribute("testToDetail", new Test());
        return "testing";
    }
    @PostMapping("/addTest")
    public String addTest(@ModelAttribute Test test,
                          @SessionAttribute("testy") Set<Test> tests,
                          Model model){
        Test newTest = testService.addTest(test.getTestName());
        tests.add(newTest);
        model.addAttribute("test" ,new Test());
        return "testing";
    }

    @GetMapping("/testDetails")
    public String testDetails(@ModelAttribute("testToDetail") Test test,
                              Long testid,
                              Model model){
        Test test1 = testService.findById(testid);
        Set<Question> questions = questionService.findQuestionsOfTest(test1);
        model.addAttribute("test", test1);
        model.addAttribute("pytaniatestu", questions);
        model.addAttribute("testToDetail", new Test());
        return "testDetails";
    }


    @ModelAttribute(name = "module")
    public String module() {
        return "testing";
    }
}
