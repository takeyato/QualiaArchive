package jp.co.quest.QualiaArchive.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jp.co.quest.QualiaArchive.entity.Employee;
import jp.co.quest.QualiaArchive.entity.EmployeeRepository;
import jp.co.quest.QualiaArchive.entity.EmployeeStatus;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<EmployeeStatus> getAllStatuses() {
        return repo.findAll().stream()
                .map(this::convertToStatus)
                .collect(Collectors.toList());
    }

    /**
     * Employee → EmployeeStatus 変換
     * @param e
     * @return
     */
    private EmployeeStatus convertToStatus(Employee e) {
        EmployeeStatus s = new EmployeeStatus();
        s.id = e.getId();
        s.name = e.getName();
        s.emoji = e.getEmoji();
        s.motto = e.getMotto();

        // 休憩中かどうか
        s.onBreak = e.isOnBreak();

        // 休憩未取得時間
        LocalDateTime lastEnd = e.getLastBreakEnd();
        if (lastEnd == null) {
            s.minutesSinceLastBreak = 999; // 初回
        } else {
            s.minutesSinceLastBreak = Duration.between(lastEnd, LocalDateTime.now()).toMinutes();
        }

        // 休憩残り時間
        if (s.onBreak) {
            long sec = Duration.between(LocalDateTime.now(), e.getLastBreakStart().plusMinutes(10)).getSeconds();
            s.breakRemaining = Math.max(sec, 0);
        } else {
            s.breakRemaining = 0;
        }

        return s;
    }

    // 休憩開始
    public void startBreak(Long id) {
        Employee e = repo.findById(id);
        e.setLastBreakStart(LocalDateTime.now());
        repo.save(e);
    }

    // 休憩終了
    public void endBreak(Long id) {
        Employee e = repo.findById(id);
        e.setLastBreakEnd(LocalDateTime.now());
        e.setLastBreakStart(null); // ← 休憩中判定の安全性UP
        repo.save(e);
    }

    // プロフィール更新（絵文字・座右の銘）
    public void updateProfile(Long id, String emoji, String motto) {
        Employee e = repo.findById(id);

        if (emoji != null && !emoji.isBlank()) {
            e.setEmoji(emoji);
        }
        if (motto != null && !motto.isBlank()) {
            e.setMotto(motto);
        }

        repo.save(e);
    }
}
