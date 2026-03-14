package jp.co.quest.QualiaArchive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.quest.QualiaArchive.bean.ScheduleItem;
import jp.co.quest.QualiaArchive.service.MailService;

@Controller
public class ReminderController {

    @Autowired
    private MailService mailService;

    @GetMapping("/reminder")
    public String sendReminder(Model model) {

        try {
            mailService.sendReminderMail(
                    "test@gmail.com",
                    "【リマインド】明日のご予定",
                    java.util.List.of(
                            new ScheduleItem("定例ミーティング", "2026-03-15 10:00", "オンライン"),
                            new ScheduleItem("ランチミーティング", "2026-03-15 12:00", "カフェ")));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "メール送信に失敗しました");
        }

        model.addAttribute("message", "メールを送信しました");
        return "reminder"; // templates/reminder.html を表示
    }

    @GetMapping("/reminder2")
    public String sendReminder2(Model model) {

        try {
            mailService.sendReminderMail(
                    "test@gmail.com",
                    "【リマインド】明日のご予定",
                    java.util.List.of(
                            new ScheduleItem("定例ミーティング", "2026-03-15 10:00", "オンライン"),
                            new ScheduleItem("ランチミーティング", "2026-03-15 12:00", "カフェ")));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "メール送信に失敗しました");
        }

        model.addAttribute("message", "メールを送信しました");
        return "reminder"; // templates/reminder.html を表示
    }
}
