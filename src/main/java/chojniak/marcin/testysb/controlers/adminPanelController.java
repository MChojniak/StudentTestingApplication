package chojniak.marcin.testysb.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class adminPanelController {
    @GetMapping("/administracja")
    public String adminPanel() {
        return "administracja";
    }
}
