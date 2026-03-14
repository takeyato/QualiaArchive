package jp.co.quest.QualiaArchive.service;

import java.util.List;

import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jp.co.quest.QualiaArchive.bean.ScheduleItem;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendReminderMail(String to, String subject, List<ScheduleItem> scheduleList) throws Exception {

        // Thymeleaf に渡すデータ
        Context context = new Context();
        context.setVariable("name", "山田太郎");
        context.setVariable("scheduleList", scheduleList); // ← 複数予定を渡す

        // HTML をテンプレートから生成
        String html = templateEngine.process("mail/reminder", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);
        helper.setFrom("QualiaArchive@gmail.com", "QualiaArchive");

        mailSender.send(message);
    }
}
