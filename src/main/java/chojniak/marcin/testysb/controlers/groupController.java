package chojniak.marcin.testysb.controlers;


import chojniak.marcin.testysb.users.User;
import chojniak.marcin.testysb.users.UserService;
import chojniak.marcin.testysb.users.groups.Group;
import chojniak.marcin.testysb.users.groups.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@SessionAttributes("grupy")
@RequestMapping
public class groupController {
    private UserService userService;
    private GroupService groupService;

    @Autowired
    public groupController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping("/utworzGrupe")
    public String grupy(Model model) {
        Set<Group> groups = groupService.findAllGroups();
        model.addAttribute("grupa", new Group());
        model.addAttribute("grupy", groups);
        return "utworzGrupe";
    }

    @PostMapping("/dodajGrupe")
    public String dodajGrupe(Model model,
                             @SessionAttribute("grupy") Set<Group> groups,
                             Group group) {

        group = groupService.addGroup(group.getName());
        groups.add(group);
        model.addAttribute("grupa", new Group());
        return "utworzGrupe";
    }


}
