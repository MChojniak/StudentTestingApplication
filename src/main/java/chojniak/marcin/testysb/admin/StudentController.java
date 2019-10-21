package chojniak.marcin.testysb.admin;

import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@SessionAttributes("studenci")
@RequestMapping("/admin")
public class StudentController {

    private UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/student")
    public String listaStudentow(Model model) {
        Set<User> studenci = userService.listUsers();
        model.addAttribute("studenci", studenci);
        model.addAttribute("student", new User());
        return "student";
    }

    @PostMapping("/dodajStudenta")
    public String dodajStudenta(@ModelAttribute User student, @SessionAttribute Set<User> studenci, Model model) {
        User registeredUser = userService.registerNewUser(student.getFirstName(), student.getLastName(), student.getEmail(), student.getPassword());
        studenci.add(registeredUser);
        model.addAttribute("student", new User());
        return "student";
    }

    @ModelAttribute(name = "module")
    public String module() {
        return "studenci";
    }

}
