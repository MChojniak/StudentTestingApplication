package chojniak.marcin.testysb.admin;

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
@SessionAttributes({"grupa","usersOutOfGroup","usersToGroup"})
@RequestMapping
public class UsersToGroupsController {
    private UserService userService;
    private GroupService groupService;

    @Autowired
    public UsersToGroupsController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }



    @GetMapping("/dodajDoGrupy")
    public String dodajDoGrupy(Model model,
                               Long grupaid,
                               HttpSession session){
        Group group = groupService.findGroupById(grupaid);
        Set<User> users = userService.findAllUsersButGroup(group);
        model.addAttribute("grupa",group);
        model.addAttribute("usersOutOfGroup", users);
        return "uzytkownicyDoGrup";
    }

    @GetMapping("/dodajDoGrupyy")
    public String dodajUsera(Model model,
                             Long grupaid,
                             String usermail,
                             HttpSession session){
        Group group = groupService.findGroupById(grupaid);
        User user = userService.findByEmail(usermail);
        group.getUsers().add(user);
        Set<User> users = userService.findAllUsersButGroup(group);
        users.remove(user);
        model.addAttribute("grupa",group);
        model.addAttribute("usersOutOfGroup", users);
        return "uzytkownicyDoGrup";
    }

}
