package chojniak.marcin.testysb.admin;


import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.TestService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@SessionAttributes({"pytania","testy","tescik"})
@RequestMapping
public class QuestionController {
    private QuestionService questionService;
    private TestService testService;

    @Autowired
    public QuestionController(QuestionService questionService,
                              TestService testService) {
        this.questionService = questionService;
        this.testService = testService;
    }

    @GetMapping("/question")
    public String questionList(Model model,
                               Long testid,
                               HttpSession session){

        Test test = testService.findById(testid);
        Set<Question> questions = test.getQuestions();
        model.addAttribute("tescik", test);
        model.addAttribute("pytania", questions);
        model.addAttribute("pytanie", new Question());
        return "question";
    }


    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute Question question,
                              @SessionAttribute("pytania") Set<Question> questions,
                              Model model,
                              @SessionAttribute("tescik") Test test){

        Question newQuestion = new Question(question.getQuestionContent(),question.getQuestionValue());
        newQuestion.setTest(test);
        questionService.addQuestion(newQuestion);
        questions.add(newQuestion);
        model.addAttribute("pytanie", new Question());
        return "question";
    }

    @ModelAttribute(name = "module")
    public String module() {
        return "pytanka";
    }
}
