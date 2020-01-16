package chojniak.marcin.testysb.admin;

import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import chojniak.marcin.testysb.users.UserService;
import chojniak.marcin.testysb.users.groups.GroupService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes({"odpowiedzi", "question"})
@RequestMapping
public class AddAnswersController {
    private QuestionService questionService;
    private AnswersService answersService;


    @Autowired
    public AddAnswersController(QuestionService questionService, AnswersService answersService) {
        this.questionService = questionService;
        this.answersService = answersService;
    }

    @GetMapping("/dodajOdpowiedz")
    public String dodajOdpowiedz(Model model,
                                 Long questionid,
                                 HttpSession session) {
        Question question = questionService.findById(questionid);
        Set<Answers> answers = question.getAnswers();


        model.addAttribute("question", question);
        model.addAttribute("odpowiedzi", answers);
        model.addAttribute("odp", new Answers());
        return "answersForm";
    }

    @GetMapping("/pokazOdpowiedzi")
    public String pokazOdpowiedzi(Model model,
                                  Long questionid) {
        Question question = questionService.findById(questionid);
        Set<Answers> answers = question.getAnswers();

        model.addAttribute("question", question);
        model.addAttribute("odpowiedzi", answers);
        model.addAttribute("odp", new Answers());
        return "answersList";
    }

    @PostMapping("/dodawanieOdpowiedzi")
    public String dodawanieOdpowiedzi(@ModelAttribute("odp") Answers answer,
                                      @SessionAttribute("odpowiedzi") Set<Answers> answers,
                                      @SessionAttribute("question") Question question,
                                      Model model,
                                      HttpSession session) {
        Answers answered = new Answers();
        answered.setAnswerContent(answer.getAnswerContent());
        answered.setCorrect(answer.getCorrect());
        answered.setQuestion(question);
        answered = answersService.addAnswer(answered);
        answers.add(answered);
        // model.addAttribute("question", question);
        model.addAttribute("odp", new Answers());
        return "redirect:/utworzTest";
    }

}
