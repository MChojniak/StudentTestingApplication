package chojniak.marcin.testysb.admin;

import chojniak.marcin.testysb.testy.answers.Answers;
import chojniak.marcin.testysb.testy.answers.AnswersService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class StudentTestViewController {

    private UserTestService userTestService;
    private UserService userService;
    private UserQuestionService userQuestionService;
    private AnswersService answersService;


    @Autowired
    public StudentTestViewController(UserTestService userTestService, UserService userService, UserQuestionService userQuestionService, AnswersService answersService) {
        this.userTestService = userTestService;
        this.userService = userService;
        this.userQuestionService = userQuestionService;
        this.answersService = answersService;
    }

    @GetMapping("/pokazTestyDlaAdmina")
    public String studentsTestsForAdmin(Model model, Principal principal){
        String userMail = principal.getName();
        User user = userService.findByEmail(userMail);
        Set<User> allUsers = userService.findAllNotAdmins();

        model.addAttribute("allUsers", allUsers);

        return "testyDlaAdmina";
    }

    @GetMapping("/selectedUserTests")
    public String selectedStudentTestForAdmin(Model model,
                                              Long userId){
        User user = userService.findById(userId);
        Set<UserTest> userTests = userTestService.findUserTestByUser(user);

        model.addAttribute("selectedUser", user);
        model.addAttribute("userTests", userTests);
        return "selectedUserTestPage";
    }

    @GetMapping("/checkClosedTest")
    public String checkClosedTestForUser(Model model,
                                         Long testid){
        UserTest userTest = userTestService.findUserTestById(testid);
        User user = userTest.getUser();


        // dodanie tymczasowego obiektu answer który pozwala
        // na odkrycie kontentu odpowiedzi udzielonej przez uzytkownika
        for(UserQuestion q : userTest.getUserQuestions()){
            if(q.getAnswerId() != -1) {
                Long answerId = q.getAnswerId();
                q.setAnswerByUser(answersService.findAnswerById(answerId));
            }else{
                Answers answers = new Answers();
                answers.setAnswerContent("Brak odpowiedzi");
                q.setAnswerByUser(answers);
            }

        }


        model.addAttribute("userTest", userTest);
        model.addAttribute("selectedUser", user);
        return "chosenTest";
    }
}
