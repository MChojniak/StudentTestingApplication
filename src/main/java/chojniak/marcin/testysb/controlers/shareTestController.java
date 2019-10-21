package chojniak.marcin.testysb.controlers;


import chojniak.marcin.testysb.testy.Test;
import chojniak.marcin.testysb.testy.TestService;
import chojniak.marcin.testysb.testy.question.Question;
import chojniak.marcin.testysb.users.*;
import chojniak.marcin.testysb.users.groups.Group;
import chojniak.marcin.testysb.users.groups.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("testy")
@RequestMapping
public class shareTestController {
    private TestService testService;
    private UserTestService userTestService;
    private UserQuestionService userQuestionService;
    private GroupService groupService;

    @Autowired
    public shareTestController(UserTestService userTestService, UserQuestionService userQuestionService, GroupService groupService, TestService testService) {
        this.userTestService = userTestService;
        this.userQuestionService = userQuestionService;
        this.groupService = groupService;
        this.testService = testService;
    }


    @GetMapping("/udostepnijGrupie")
    public String testSharingMethod(Model model,
                                    Long grupaid,
                                    HttpSession session){
        Set<Test> testSet = testService.findAllTests();
        testSet = testSet.stream().filter(u->u.getQuestions().size()>=10).collect(Collectors.toSet());
        Group group = groupService.findGroupById(grupaid);


        model.addAttribute("wybranaGrupa", group);
        model.addAttribute("testy", testSet);
        return "udostepnijGrupie";
    }


    @GetMapping("/udostepnijGrupiePost")
    public String testSharingMethodPost(Model model,
                                        Long grupaid,
                                        Long testid,
                                        @SessionAttribute("testy") Set<Test> testSet){
        Group group = groupService.findGroupById(grupaid);
        Test test = testService.findById(testid);
        Set<User> userSet = group.getUsers();
        Set<UserTest> userTest = userTestService.addUserTests(userSet,test);
        Set<Question> questions = test.getQuestions();
        for(UserTest ut : userTest) {
            Set<UserQuestion> userQuestions = userQuestionService.addUserQuestionsToUserTest(questions, ut);
        }
        model.addAttribute("wybranaGrupa", group);
        model.addAttribute("testy", testSet);
        return "udostepnijGrupie";
    }




}
