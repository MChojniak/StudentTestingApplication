package chojniak.marcin.testysb.controlers.uzytkownik;


import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserService;
import chojniak.marcin.testysb.users.UserTest;
import chojniak.marcin.testysb.users.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"uzytkownik", "userTest"})
@RequestMapping
public class TestViewsController {
    private UserService userService;
    private UserTestService userTestService;

    @Autowired
    public TestViewsController(UserService userService, UserTestService userTestService) {
        this.userService = userService;
        this.userTestService = userTestService;
    }

    @GetMapping("/closedTests")
    public String testsViewForUser(Model model, Principal principal){

        String userMail = principal.getName();
        User user = userService.findByEmail(userMail);
        Set<UserTest> testSet = userTestService.findUserTestByUser(user);
        testSet = testSet.stream().filter(u->u.getTestAvailable()==false).collect(Collectors.toSet());

        model.addAttribute("uzytkownik", user);
        model.addAttribute("testList", testSet);
        return "twojeTesty";
    }
}
