package jp.co.quest.QualiaArchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QA001Controller {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Thymeleaf へようこそ！");
        return "index";  // templates/index.html を表示
    }
}
