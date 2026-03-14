package jp.co.quest.QualiaArchive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailPreviewController {

    @GetMapping("/mail/preview")
    public String previewMail(Model model) {

        // テスト用のダミーデータ
        model.addAttribute("name", "山田太郎");
        model.addAttribute("title", "定例ミーティング");
        model.addAttribute("datetime", "2026-03-15 10:00");
        model.addAttribute("place", "オンライン");

        // メールテンプレートをそのまま返す
        return "mail/reminder";
    }
}
