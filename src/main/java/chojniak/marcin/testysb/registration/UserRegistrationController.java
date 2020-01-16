package chojniak.marcin.testysb.registration;

import chojniak.marcin.testysb.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reg")
public class UserRegistrationController {

    private UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registerNewUser")
    public String registerNewUser(@Validated RegistrationForm registrationForm, BindingResult bindingResult, Model model) {

        if (!registrationForm.passwordConfirmationCorrect()) {
            FieldError error = new FieldError("registrationForm",
                    "passwordConfirmation",
                    null,
                    false,
                    new String[]{"passwordConfirmation", "registrationForm.passwordConfirmation"},
                    null,
                    "Hasła muszą być zgodne!");
            bindingResult.addError(error);
        }
        if (!registrationForm.getEmail().isEmpty()) {
            if (this.userService.checkIfEmailExist(registrationForm.getEmail())) {
                FieldError error = new FieldError(
                        "registrationForm",
                        "email",
                        null,
                        false,
                        new String[]{"email",
                                "registrationForm.email"},
                        null,
                        "Taki e-mail już istnieje!");
                bindingResult.addError(error);
            }
        }
        if (!bindingResult.hasErrors()) {
            this.userService.registerNewUser(
                    registrationForm.getFirstName(),
                    registrationForm.getLastName(),
                    registrationForm.getEmail(),
                    registrationForm.getPassword()
            );
            return "redirect:/login?registered=true";
        }
        model.addAttribute("registrationForm", registrationForm);
        model.addAttribute("formValidated", true);
        return "registration";
    }
}
