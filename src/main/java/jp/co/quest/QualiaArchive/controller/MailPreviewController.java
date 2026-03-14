package jp.co.quest.QualiaArchive.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.quest.QualiaArchive.bean.ScheduleItem;

@Controller
public class MailPreviewController {

    @GetMapping("/mail/preview")
    public String previewMail(Model model) {

        List<ScheduleItem> list = new ArrayList<>();

        list.add(new ScheduleItem(
                "終日研修",
                LocalDateTime.of(2026, 3, 15, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 0),
                "会議室B",
                "#c3c3c3"));

        list.add(new ScheduleItem(
                "定例ミーティング",
                LocalDateTime.of(2026, 3, 15, 10, 0),
                LocalDateTime.of(2026, 3, 15, 11, 0),
                "オンライン",
                "#FF0000"));

        // テスト用のダミーデータ
        model.addAttribute("name", "山田太郎");
        model.addAttribute("scheduleList", list);

        // メールテンプレートをそのまま返す
        return "mail/reminder";
    }

    @GetMapping("/mail/preview2")
    public String previewMail2(Model model) {

        List<ScheduleItem> list = new ArrayList<>();

        list.add(new ScheduleItem(
                "終日研修",
                LocalDateTime.of(2026, 3, 15, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 0),
                "会議室B",
                "#FF0000"));

        // テスト用のダミーデータ
        model.addAttribute("name", "山田太郎");
        model.addAttribute("scheduleList", list);

        // メールテンプレートをそのまま返す
        return "mail/reminder";
    }

    @GetMapping("/mail/preview3")
    public String previewMail3(Model model) {

        List<ScheduleItem> list = new ArrayList<>();

        list.add(new ScheduleItem(
                "終日研修",
                LocalDateTime.of(2026, 3, 15, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 0),
                "会議室B",
                "#FF0000"));

        list.add(new ScheduleItem(
                "定例ミーティング",
                LocalDateTime.of(2026, 3, 15, 10, 0),
                LocalDateTime.of(2026, 3, 15, 11, 0),
                "オンライン",
                "#00FF00"));

        // テスト用のダミーデータ
        model.addAttribute("name", "山田太郎");
        model.addAttribute("scheduleList", list);

        // メールテンプレートをそのまま返す
        return "mail/reminder";
    }
}
