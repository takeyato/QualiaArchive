package jp.co.quest.QualiaArchive.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /** リマインドメール送信のテスト用 */
    @GetMapping("/reminder")
    public String sendReminder(Model model) {

        try {
            List<ScheduleItem> list = new ArrayList<>();

            list.add(new ScheduleItem(
                    "終日研修",
                    LocalDateTime.of(2026, 3, 15, 0, 0),
                    LocalDateTime.of(2026, 3, 16, 0, 0),
                    "会議室B",
                    "#c3c3c3"
            ));

            list.add(new ScheduleItem(
                    "定例ミーティング",
                    LocalDateTime.of(2026, 3, 15, 10, 0),
                    LocalDateTime.of(2026, 3, 15, 11, 0),
                    "オンライン",
                    "#00FF00"
            ));

            
            mailService.sendReminderMail(
                    "test@gmail.com",
                    "【リマインド】明日の予定一覧",
                    list
            );
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
            List<ScheduleItem> list = new ArrayList<>();

            list.add(new ScheduleItem(
                    "定例ミーティング",
                    LocalDateTime.of(2026, 3, 15, 10, 0),
                    LocalDateTime.of(2026, 3, 15, 11, 0),
                    "オンライン",
                    "#c3c3c3"
            ));

            
            mailService.sendReminderMail(
                    "test@gmail.com",
                    "【リマインド】明日の予定一覧",
                    list
            );
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "メール送信に失敗しました");
        }

        model.addAttribute("message", "メールを送信しました");
        return "reminder"; // templates/reminder.html を表示
    }
}
