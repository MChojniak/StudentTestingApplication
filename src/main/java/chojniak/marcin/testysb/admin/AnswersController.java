package chojniak.marcin.testysb.admin;


import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.testy.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@SessionAttributes({"odpowiedzi", "pytania"})
@RequestMapping()
public class AnswersController {
    private AnswersService answersService;
    private QuestionService questionService;

    @Autowired
    public AnswersController(AnswersService answersService, QuestionService questionService) {
        this.answersService = answersService;
        this.questionService = questionService;
    }

    @GetMapping("/answers")
    public String listAnswers(Model model) {
        Set<Answers> answersSet = answersService.findAll();
        Set<Question> questions = questionService.findAllQuestions();

        model.addAttribute("odpowiedzi", answersSet);
        model.addAttribute("pytania", questions);
        model.addAttribute("odpowiedz", new Answers());
        return "answers";
    }

    @PostMapping("/addAnswers")
    public String addAnswer(@ModelAttribute Answers answer,
                            @SessionAttribute("odpowiedzi") Set<Answers> answersSet,
                            @SessionAttribute("pytania") Set<Question> questions,
                            Model model) {

        Answers newAnswer = new Answers();// = new Answers(answer.getAnswerContent(),answer.getCorrect());
        newAnswer.setAnswerContent(answer.getAnswerContent());
        newAnswer.setCorrect(answer.getCorrect());
        Question newQuestion = questionService.findById(answer.getTempId());
        newAnswer.setQuestion(newQuestion);
        newAnswer = answersService.addAnswer(newAnswer);
        answersSet.add(newAnswer);
        model.addAttribute("odpowiedz", new Answers());
        return "answers";
    }

    @ModelAttribute(name = "module")
    public String module() {
        return "odpowiedzi";
    }

}
