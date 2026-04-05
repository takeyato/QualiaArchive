package jp.co.quest.QualiaArchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KeyBoardController {

    @GetMapping("/keyboard")
    public String index(Model model) {
        model.addAttribute("message", "Thymeleaf へようこそ！");
        return "keyboard";  // templates/index.html を表示
    }
}
