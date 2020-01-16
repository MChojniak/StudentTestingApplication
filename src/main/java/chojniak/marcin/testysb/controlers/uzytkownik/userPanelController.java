package chojniak.marcin.testysb.controlers.uzytkownik;

import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"uzytkownik", "userPanelForm", "userTest"})
@RequestMapping
public class userPanelController {
    private UserService userService;
    private UserTestService userTestService;
    private AnswersService answersService;
    private UserQuestionService userQuestionService;

    @Autowired
    public userPanelController(UserService userService, UserTestService userTestService, AnswersService answersService, UserQuestionService userQuestionService) {
        this.userService = userService;
        this.userTestService = userTestService;
        this.answersService = answersService;
        this.userQuestionService = userQuestionService;
    }


    @GetMapping("/panelUzytkownika")
    public String userTesty(Model model, Principal principal) {
        String userMail = principal.getName();
        User user = userService.findByEmail(userMail);
        Set<UserTest> testSet = userTestService.findUserTestByUser(user);
        testSet = testSet.stream().filter(u -> u.getTestAvailable() == true).collect(Collectors.toSet());

        model.addAttribute("uzytkownik", user);
        model.addAttribute("testList", testSet);
        return "panelUzytkownika";
    }

    @GetMapping("/testSolve")
    public String testSolving(Model model,
                              @SessionAttribute("uzytkownik") User user,
                              Long userTestId) {
        UserTest userTest = userTestService.findUserTestById(userTestId);
        Set<UserQuestion> userQuestionSet = userTest.getUserQuestions();
        UserPanelForm userPanelForm = new UserPanelForm();
        userPanelForm.setUserQuestionSet(userQuestionSet);
        model.addAttribute("userTest", userTest);
        model.addAttribute("userPanelForm", userPanelForm);
        model.addAttribute("uzytkownik", user);
        return "testSolvePage";
    }

    @PostMapping("/testSolveSubmit")
    public String testSolvingEnd(Model model,
                                 @SessionAttribute("uzytkownik") User user,
                                 UserPanelForm userPanelForm,
                                 @SessionAttribute("userTest") UserTest userTest) {
        userPanelForm.getUserQuestionSet().stream().forEach(q -> System.out.printf("question %d selected :%d\n", q.getId(), q.getAnswerId()));
        int score = 0;
        int maxscore = 0;
        userTest.setUserQuestions(userPanelForm.getUserQuestionSet());
        for (UserQuestion q : userTest.getUserQuestions()) {
            if (q.getAnswerId() > 0) {
                Answers answers = answersService.findAnswerById(q.getAnswerId());
                userQuestionService.saveAnsweredId(q, q.getAnswerId());
                if (answers.getCorrect() == true) {
                    score += q.getQuestion().getQuestionValue();
                    maxscore += q.getQuestion().getQuestionValue();
                } else maxscore += q.getQuestion().getQuestionValue();
            } else maxscore += q.getQuestion().getQuestionValue();
        }

        userTestService.closeUserTest(userTest, score, maxscore);


        return "redirect:/panelUzytkownika";
    }

}
